package com.zfx.supper.cofig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @description:
 * @author: zheng-fx
 * @time: 2019/11/17 0017 00:30
 */
//@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /*
     * 功能描述: 静态资源路径，springboot默认的静态资源在resources/static下，如果在别的位置下，可自行修改
     * 
     * @Param: [registry]
     * @Return: void
     * @Author: Administrator
     * @Date: 2019/11/17 0017 0:34
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/*");
    }
}
