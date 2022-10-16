package com.lczyfz.edp.springboot.demo.sys.util;

import com.alibaba.fastjson.JSONObject;
import com.lczyfz.edp.springboot.core.utils.SpringContextHolder;
import com.lczyfz.edp.springboot.demo.sys.entity.CorrectInfo;
import com.lczyfz.edp.springboot.demo.sys.entity.TestGroup;
import com.lczyfz.edp.springboot.demo.sys.mapper.CorrectInfoMapper;
import com.lczyfz.edp.springboot.demo.sys.mapper.TestGroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author 86189
 */
@Component
@Slf4j
public class DelayUtils implements Runnable, InitializingBean {

    public static DelayUtils delayUtils = null;

    private static DelayQueue delayQueue = new DelayQueue();

    private static AtomicBoolean isrun = new AtomicBoolean(true);

    /**
     * 初始化 redis hash key
     */
    private static final List<String> delayKeys = new ArrayList<String>() {{

    }};

    @Autowired
    private RedisTemplate redisTemplate;

    public DelayUtils(RedisTemplate redisTemplate) {
        super();
        this.redisTemplate = redisTemplate;
    }

    /**
     * 加入延时队列
     * @param object
     * @param time
     */
    public void setDelay(String delayKey, Map<String, Object> object, long time) {
        try {
            String json = JSONObject.toJSONString(object);
            DelayedTask delayedTask = new DelayedTask(json, time);
            //redis持久化，防止数据丢失
            Map map = redisTemplate.opsForHash().entries(delayKey);
            map = map == null ? new HashMap() : map;
            map.put(object.get("id"), delayedTask);
            redisTemplate.opsForHash().putAll(delayKey, map);
            delayQueue.offer(delayedTask);
        } catch (NumberFormatException e) {
            log.error("队列添加数据异常{}", e);
        }
    }

    /**
     * 从队列删除
     * @param id
     */
    public void deleteForDelay(String delayKey, String id) {
        try {
            redisTemplate.opsForHash().delete(delayKey, id);
            if(isrun.get()) {
                Object[] objects = delayQueue.toArray();
                if(objects != null && objects.length > 0) {
                    for (Object object : objects) {
                        DelayedTask delayedTask = (DelayedTask) object;
                        Map set = JSONObject.parseObject(delayedTask.getTaskJson(), Map.class);
                        if(set != null && Integer.valueOf(set.get("id").toString()).equals(id)) {
                            delayQueue.remove(delayedTask);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("队列删除数据异常{}", e);
        }
    }

    /**
     * 延迟队列线程方法
     */
    @Override
    public void run() {
        try {
            while (isrun.get()) {
                Delayed take = delayQueue.take();
                if(take != null) {
                    DelayedTask delayedTask = (DelayedTask) take;
                    String taskJson = delayedTask.getTaskJson();
                    Map map = JSONObject.parseObject(taskJson, Map.class);

                    
                    if(map != null) {
                        //业务逻辑
                        TestGroupMapper testGroupMapper = SpringContextHolder.getBean(TestGroupMapper.class);
                        String id = map.get("id").toString();
                        int type= (int) map.get("type");
                        Object testGroupId = map.get("testGroupId");

                        TestGroup testGroup = testGroupMapper.selectByPrimaryKey(Long.valueOf((Integer)testGroupId));
                        if(type==1){

                            if ("ing".equals(testGroup.getStatus())){
                                testGroup.setStatus("end");

                                testGroup.setUpdateDate(new Date());

                                testGroup.setNewRecord(false);

                                testGroupMapper.updateByPrimaryKey(testGroup);

                                CorrectInfoMapper correctInfoMapper=SpringContextHolder.getBean(CorrectInfoMapper.class);

                                CorrectInfo correctInfo=new CorrectInfo(map.get("stuId").toString(),"null", Long.valueOf((Integer)testGroupId));

                                correctInfoMapper.insert(correctInfo);
                            }
                        }else{
                            if("waitCorrect".equals(testGroup.getStatus())){
                                EmailUtil.emailSend(map.get("email").toString(),"您的学生已提交"+map.get("testName").toString()
                                        +"试卷答案超50分钟，请尽快批阅。");
                            }
                        }

                        deleteForDelay(id,id);
                    }
                }
            }
        }catch (Exception e) {
            log.error("延时队列执行异常 {}", e);
        }
    }

    /**
     * 初始化数据
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() {
        if(delayUtils == null) {
            delayUtils = new DelayUtils(redisTemplate);
        }
        try {
            Thread thread = new Thread(delayUtils);
            thread.start();
            //启动项目，将redis缓存加载到内存
            for (String delayKey : delayKeys) {
                Map<String, Object> map = redisTemplate.opsForHash().entries(delayKey);
                if(map != null && map.size() > 0) {
                    for (String key : map.keySet()) {
                        DelayedTask delayedTask = (DelayedTask) map.get(key);
                        delayQueue.offer(delayedTask);
                    }
                }
            }
        } catch (Exception e) {
            log.error("初始化队列数据异常 {}", e);
        }
    }
}
