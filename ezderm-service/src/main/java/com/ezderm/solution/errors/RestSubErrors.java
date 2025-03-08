package com.ezderm.solution.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestSubErrors {
	private Object object;
	private String developerMessage;
    private String userMessage;
	
}
