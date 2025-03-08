package com.ezderm.solution.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ezderm.solution.utils.CustomHttpHeaders;

@Configuration
public class SecurityConfig {

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers", 
													  "Access-Control-Allow-Origin",
													  "Access-Control-Request-Method", 
													  "Access-Control-Request-Headers", 
													  "Origin", "Cache-Control",
													  "Content-Type", "Authorization"));
		configuration.addExposedHeader("Content-Disposition");
		configuration.addExposedHeader(CustomHttpHeaders.XUsername);
		configuration.setAllowedMethods(Arrays.asList("DELETE", "GET", "POST", "PATCH", "PUT"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
