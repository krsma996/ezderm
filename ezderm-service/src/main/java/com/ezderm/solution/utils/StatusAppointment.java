package com.ezderm.solution.utils;

public enum StatusAppointment {
	
	CREATED("CREATED"),
	CANCELED("CANCELED")
	;

	
	private String statusValue;

	private StatusAppointment(String statusValue) {
		this.statusValue = statusValue;
	}
	
	public String getStatusValue() {
		return statusValue;
	}
	
}
