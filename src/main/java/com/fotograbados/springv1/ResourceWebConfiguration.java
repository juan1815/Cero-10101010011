package com.fotograbados.springv1;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceWebConfiguration implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/custom-images/**")
				.addResourceLocations("file:images/");

		// Registrar una ruta para servir imágenes desde la ubicación estática por defecto
		registry.addResourceHandler("/images/**")
				.addResourceLocations("classpath:/static/images/");
	}

}
