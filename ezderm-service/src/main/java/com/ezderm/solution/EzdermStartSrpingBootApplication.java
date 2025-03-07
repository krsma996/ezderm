package com.ezderm.solution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources(value = { 
				@PropertySource("file:${ezderm.home.directory}/configuration.properties")})
public class EzdermStartSrpingBootApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EzdermStartSrpingBootApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EzdermStartSrpingBootApplication.class);
    }
}
