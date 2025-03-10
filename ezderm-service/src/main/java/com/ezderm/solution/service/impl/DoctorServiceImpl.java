package com.ezderm.solution.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ezderm.solution.dao.DoctorDao;
import com.ezderm.solution.dto.DoctorDto;
import com.ezderm.solution.exception.DuplicateUsernameException;
import com.ezderm.solution.exception.NotPermittedException;
import com.ezderm.solution.exception.ResourceNotFoundException;
import com.ezderm.solution.model.Doctor;
import com.ezderm.solution.service.DoctorService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class DoctorServiceImpl implements DoctorService {

	private final DoctorDao doctorDao;

	@Override
	@Transactional
	public DoctorDto saveDoctor(DoctorDto doctorDto, String userName) {
		if (this.doctorDao.existsByUsername(userName)) {
			throw new DuplicateUsernameException(" Username " + userName + " Already Exsist ");
		}

		Optional<Doctor> existingDoctorOpt = this.doctorDao.findById(doctorDto.getId());
		Doctor doctor;
		if (existingDoctorOpt.isPresent()) {
			doctor = existingDoctorOpt.get();
			log.info("Updating existing doctor with ID: {}", doctor.getId());
		} else {
			doctor = new Doctor();
			log.info("Creating new doctor...");
		}
		doctor.setFirstName(doctorDto.getFirstName());
		doctor.setLastName(doctorDto.getLastName());
		doctor.setUsername(userName);
		doctor.setUuid(doctorDto.getUuid());
		doctor = this.doctorDao.save(doctor);
		log.info("Saved doctor with ID: {}", doctor.getId());
		return new DoctorDto(doctor.getId(), doctor.getUuid(), doctor.getFirstName(), doctor.getLastName());

	}

	@Override
	@Transactional
	public DoctorDto deleteDoctor(String uuid, String userName) {
		Doctor doctor = doctorDao.findByUuid(uuid);
		if (doctor == null) {
			throw new ResourceNotFoundException("Doctor with UUID " + uuid + " not found");
		}
		if (!doctor.getUsername().equals(userName)) {
	        throw new NotPermittedException("User " + userName + " is not authorized to delete this doctor");
	    }	
		log.info("Deleting doctor with ID: {}", uuid);
		this.doctorDao.delete(doctor);
		return new DoctorDto(doctor.getId(),doctor.getUuid(),doctor.getFirstName(),doctor.getLastName());
	}

}
