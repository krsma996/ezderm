package com.ezderm.solution.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ezderm.solution.dao.PatientDao;
import com.ezderm.solution.dto.PatientDto;
import com.ezderm.solution.exception.ResourceNotFoundException;
import com.ezderm.solution.model.Patient;
import com.ezderm.solution.service.PatientService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@RequiredArgsConstructor
@Log4j2
public class PatientServiceImpl implements PatientService {

	private final PatientDao patientDao;

	@Override
	@Transactional
	public PatientDto createPatient(PatientDto patientDto) {
		Optional<Patient> existingPatientOpt = this.patientDao.findById(patientDto.getId());
		Patient patient;
		if (existingPatientOpt.isPresent()) {
			patient = existingPatientOpt.get();
			log.info("Updating existing patient with ID: {}", patient.getId());
		} else {
			patient = new Patient();
			log.info("Creating new patient...");
		}
		patient.setFirstName(patientDto.getFirstName());
		patient.setLastName(patientDto.getLastName());
		patient.setMiddleName(patientDto.getMiddleName());
		patient.setDateOfBirth(patientDto.getDateOfBirth());
		patient = this.patientDao.save(patient);
		log.info("Saved patient with ID: {}", patient.getId());
		return new PatientDto(patientDto.getId(), patientDto.getFirstName(), patientDto.getMiddleName(),
							  patientDto.getLastName(), patientDto.getDateOfBirth());
	}

	@Transactional
	@Override
	public void deletePatient(Long id) {
		 Patient patient = this.patientDao.findById(id)
			        .orElseThrow(() -> new ResourceNotFoundException("Patient with ID " + id + " not found"));
		 log.info("Deleting patient with ID: {}",id);
		 this.patientDao.delete(patient);

	}
	
	
	
	

}
