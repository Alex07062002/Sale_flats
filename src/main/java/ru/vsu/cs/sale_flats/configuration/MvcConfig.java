package ru.vsu.cs.sale_flats.configuration;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@NoArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

/*
	@Bean
	public FreeMarkerConfigurer freemarkerConfig() throws TemplateException, IOException {
		FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
		freeMarkerConfigurer.setTemplateLoaderPath("classpath:templates/");
		freemarker.template.Configuration cfg = freeMarkerConfigurer.createConfiguration();
		cfg.setNumberFormat("#");
		freeMarkerConfigurer.setConfiguration(cfg);
		return freeMarkerConfigurer;
	}*/


	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("auth");
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/account").setViewName("account");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**")
				.addResourceLocations("classpath:/static/");
	}
}
