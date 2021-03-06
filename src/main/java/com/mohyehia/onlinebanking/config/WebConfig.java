package com.mohyehia.onlinebanking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		ViewControllerRegistration r = registry.addViewController("/auth/login");
        r.setViewName("users/login");
	}
	
	@Bean
	public ViewResolver jspViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
	}
	
//	@Bean(name="messageSource")
//	public ResourceBundleMessageSource bundleMessageSource() {
//	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//	    messageSource.setBasename("messages");
//	    return messageSource;
//	}
}
