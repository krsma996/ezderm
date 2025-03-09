package com.ezderm.solution.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	
	@Bean
    public OpenAPI appApiDoc() {
        OpenAPI apiDoc = new OpenAPI();
        apiDoc.info(new Info().title("Ezderm access API")
                              .description("Ezderm and access  API - Ezderm management")
                              .version("1.0.0")
                              .license(new License().name("Copyright by ezderm")
                                                    .url("https://ezderm.com/")));
 
        return apiDoc;
    }
	
}
