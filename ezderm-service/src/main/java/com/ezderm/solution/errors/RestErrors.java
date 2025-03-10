package com.ezderm.solution.errors;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestErrors {

	private String messageTitle;
	private String messageDescription;
	private int subcode;
	private List<RestSubErrors> errors;

	public void addError(RestSubErrors error) {
		if (this.errors == null) {
			this.errors = new ArrayList<>();
		}

		this.errors.add(error);
	}
	
	public RestErrors(String messageTitle, String messageDescription, List<RestSubErrors> errors) {
    	this.messageDescription = messageDescription;
    	this.messageTitle = messageTitle;
    	this.errors = errors;
    	this.subcode = 1;
	}

	public RestErrors(String messageTitle, String messageDescription) {
    	this(messageTitle, messageDescription, 1);
	}

	public RestErrors(String messageTitle, String messageDescription, int subcode) {
    	this.messageDescription = messageDescription;
    	this.messageTitle = messageTitle;
    	this.subcode = subcode;
	}
	
}
