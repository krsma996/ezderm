package com.ezderm.solution.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ezderm.solution.dto.PatientDto;
import com.ezderm.solution.service.PatientService;
import com.ezderm.solution.web.api.PatientApi;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PatientController implements PatientApi {

	private final PatientService patientService;
	
	@Override
	public ResponseEntity<PatientDto> createPatient(@Valid PatientDto patientDto,String userName) {
		PatientDto savedPatient = this.patientService.createPatient(patientDto,userName);
	    return ResponseEntity.ok(savedPatient);
	}

	@Override
	public ResponseEntity<PatientDto> deletePatient(String uuid,String userName) {
		PatientDto deletedPatient = this.patientService.deletePatient(uuid,userName);
		return ResponseEntity.ok(deletedPatient);
	}

}
