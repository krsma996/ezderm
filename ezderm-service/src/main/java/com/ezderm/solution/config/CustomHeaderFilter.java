package com.ezderm.solution.config;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebFilter("/*")  // na sve endpointe 
@Component
public class CustomHeaderFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
		
		HttpServletRequest httpRequest  = (HttpServletRequest) request;
		HttpServletResponse httpResponse  = (HttpServletResponse) response;
		
		String xUsername = httpRequest.getHeader("X-Username");
		
		if(xUsername==null || xUsername.isEmpty()) {
			  httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing X-Username header");
			  return;
		}
        httpResponse.setHeader("X-Username", xUsername);
        chain.doFilter(request, response);	
	}

}
