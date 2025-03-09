package com.ezderm.solution.web;

import org.springframework.http.HttpStatus;
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
	public ResponseEntity<PatientDto> createPatient(@Valid PatientDto patientDto) {
		PatientDto savedPatient = this.patientService.createPatient(patientDto);
	    return ResponseEntity.ok(savedPatient);
	}

	@Override
	public ResponseEntity<Void> deletePatient(Long id) {
		this.patientService.deletePatient(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
