package com.ezderm.solution.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ezderm.solution.dao.DoctorDao;
import com.ezderm.solution.dao.PatientDao;
import com.ezderm.solution.dto.PatientDto;
import com.ezderm.solution.exception.NotPermittedException;
import com.ezderm.solution.exception.ResourceNotFoundException;
import com.ezderm.solution.model.Doctor;
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
	private final DoctorDao doctorDao;

	@Override
	@Transactional
	public PatientDto createPatient(PatientDto patientDto, String userName) {
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
		patient.setUuid(patientDto.getUuid());
		patient.setDateOfBirth(patientDto.getDateOfBirth());
		patient = this.patientDao.save(patient);
		log.info("Saved patient with ID: {}", patient.getId());
		return new PatientDto(patientDto.getId(), patientDto.getUuid(), patientDto.getFirstName(),
				patientDto.getMiddleName(), patientDto.getLastName(), patientDto.getDateOfBirth());
	}

	@Transactional
	@Override
	public PatientDto deletePatient(String uuid, String userName) {
		Patient patient = this.patientDao.findByUuid(uuid);
		Doctor foundedDoctor = this.doctorDao.findByUsername(userName);
		if (patient == null) {
			throw new ResourceNotFoundException("Patient with UUID " + uuid + " not found");
		}
		if (foundedDoctor == null) {
	        throw new ResourceNotFoundException("Doctor " + userName + " is not authorized to delete this patient");
	    }
	    if (!foundedDoctor.getUsername().equals(userName)) {
	        throw new NotPermittedException("Doctor " + userName + " is not authorized to delete this patient");
	    }
		log.info("Deleting patient with UUID : {}", patient.getUuid());
		this.patientDao.delete(patient);
		return new PatientDto(patient.getId(), patient.getUuid(), 
							  patient.getFirstName(), patient.getMiddleName(),
							  patient.getLastName(), patient.getDateOfBirth());

	}

	   

}
