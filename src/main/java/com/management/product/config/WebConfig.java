package com.management.product.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "com.management.product.controller",
        "com.management.product.config"})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Value("${view.type}")
    private String contentType;

    @Value("${view.name-prefix}")
    private String viewPrefix;

    @Value("${view.name-suffix}")
    private String viewSuffix;

    @Value("${view.expose_beans_as_attributes}")
    private boolean exposeContextBeansAsAttributes;

    @Value("${resources.handler}")
    private String resourcesHandler;

    @Value("${resources.location}")
    private String resourcesLocation;

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setContentType(contentType);
        viewResolver.setPrefix(viewPrefix);
        viewResolver.setSuffix(viewSuffix);
        viewResolver.setExposeContextBeansAsAttributes(exposeContextBeansAsAttributes);
        return viewResolver;
    }

    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry){
        resourceHandlerRegistry.addResourceHandler(resourcesHandler).
                addResourceLocations(resourcesLocation);
    }
}
