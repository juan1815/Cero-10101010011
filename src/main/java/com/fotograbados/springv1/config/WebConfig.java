package com.fotograbados.springv1.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/error/404").setViewName("errors/404");
        registry.addViewController("/error/500").setViewName("errors/500");
        registry.addViewController("/error").setViewName("errors/error");
    }
}

