package com.ezderm.solution.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ezderm.solution.dao.DoctorDao;
import com.ezderm.solution.dto.DoctorDto;
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
	public DoctorDto saveDoctor(DoctorDto doctorDto) {
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
	    doctor.setUsername(doctorDto.getUsername());
	    doctor = this.doctorDao.save(doctor);
	    log.info("Saved doctor with ID: {}", doctor.getId());
	    
	    return new DoctorDto(doctor.getId(), doctor.getFirstName(), doctor.getLastName(), doctor.getUsername());
	}

}
