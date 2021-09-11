package com.Defis.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		
		String dirName = "user-photos";
		
		Path userPhotosDir = Paths.get(dirName);
		
		String userPhotosPath = userPhotosDir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/" + dirName + "/**")
			.addResourceLocations("file:" + userPhotosPath + "/");
		
		
		String dirName2 = "agent-photos";
		
		Path agentPhotosDir = Paths.get(dirName2);
		
		String agentPhotosPath = agentPhotosDir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/" + dirName2 + "/**")
			.addResourceLocations("file:" + agentPhotosPath + "/");
		
		
		String dirName3 = "applicant-photos";
		
		Path applicantPhotosDir = Paths.get(dirName3);
		
		String applicantPhotosPath = applicantPhotosDir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/" + dirName3 + "/**")
			.addResourceLocations("file:" + applicantPhotosPath + "/");
	}	
	
}
