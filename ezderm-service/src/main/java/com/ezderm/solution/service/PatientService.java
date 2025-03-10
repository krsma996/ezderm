package com.ezderm.solution.service;

import com.ezderm.solution.dto.PatientDto;

public interface PatientService {
	
	
	PatientDto createPatient(PatientDto patientDto,String userName);
	
	void deletePatient(Long id);

}
