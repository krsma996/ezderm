package com.ezderm.solution.config;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.ezderm.solution.exception.InvalidUsernameException;

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
		String uuidPattern = "^[a-zA-Z0-9_]{4,32}$";
		
		if (xUsername == null || xUsername.isEmpty()) {
            throw new InvalidUsernameException("Missing X-Username header");
        }

        if (!xUsername.matches(uuidPattern)) {
            throw new InvalidUsernameException("Invalid X-Username format");
        }
        httpResponse.setHeader("X-Username", xUsername);
        chain.doFilter(request, response);	
	}

}
