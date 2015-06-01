package com.todo.config;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.todo")
public class AppConfig  extends WebMvcConfigurerAdapter {

	 @Bean
	    public ViewResolver viewResolver  () {
		 InternalResourceViewResolver resolver = 
	              new InternalResourceViewResolver();
	        resolver.setViewClass(JstlView.class);
	        resolver.setPrefix("WEB-INF/pages/");
	        resolver.setSuffix(".jsp");
	        return resolver;
	 }
}