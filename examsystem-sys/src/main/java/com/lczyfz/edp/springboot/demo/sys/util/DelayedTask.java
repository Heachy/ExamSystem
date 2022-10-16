package com.lczyfz.edp.springboot.demo.sys.util;

import java.io.Serializable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author 86189
 */
public class DelayedTask implements Delayed, Serializable {

    private String taskJson ;
    /**开始时间
     *
     */
    private final long start = System.currentTimeMillis();

    private final long time ;


    public DelayedTask(String taskJson, long time) {
        super();
        this.taskJson = taskJson;
        this.time = time;
    }

    public String getTaskJson() {
        return taskJson;
    }

    public void setTaskJson(String taskJson) {
        this.taskJson = taskJson;
    }

    @Override
    public String toString() {
        return "OrderDelayedTask [orderJson=" + taskJson + ", start=" + start + ", time=" + time + "]";
    }

    /**
     * 需要实现的接口，获得延迟时间   用过期时间-当前时间
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert((start+time) - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    /**
     * 用于延迟队列内部比较排序   当前时间的延迟时间 - 比较对象的延迟时间
     */
    @Override
    public int compareTo(Delayed o) {
        DelayedTask o1 = (DelayedTask) o;
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }
}
