package com.lczyfz.edp.springboot.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author maple
 * @date 2019-07-18
 */
@Configuration
public class EdpWebMvcConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
//    }

    @Value("${resourceMapPath}")
    public String resourceMapPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置静态资源处理
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations( "file:"+resourceMapPath);
    }
}
