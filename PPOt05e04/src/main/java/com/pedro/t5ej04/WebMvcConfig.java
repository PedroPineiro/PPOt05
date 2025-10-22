package com.pedro.t5ej04;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/enlaces-externos").setViewName("enlaces-externos");
        registry.addViewController("/galeria-fotos").setViewName("galeria-fotos");
    }
    
}
