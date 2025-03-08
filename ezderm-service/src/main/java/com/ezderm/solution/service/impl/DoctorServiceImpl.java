package com.ezderm.solution.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ezderm.solution.dao.DoctorDao;
import com.ezderm.solution.dto.DoctorDto;
import com.ezderm.solution.model.Doctor;
import com.ezderm.solution.service.DoctorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class DoctorServiceImpl implements DoctorService {
	
	private final DoctorDao doctorDao;
	

	@Override
	public DoctorDto saveDoctor(DoctorDto doctorDto) {
		Optional<Doctor> foundedDoctor = this.doctorDao.findById(doctorDto.getId());
		if(!foundedDoctor.isPresent()) {
			foundedDoctor = Optional.ofNullable(new Doctor());
			Doctor doctor = foundedDoctor.get();	
			doctor.setId(doctor.getId());
			doctor.setFirstName(doctorDto.getFirstName());
			doctor.setLastName(doctorDto.getLastName());
			doctor.setUsername(doctorDto.getUsername());
			log.info("Saved doctor to db...");
			this.doctorDao.save(doctor);		
		}
		return doctorDto;
	}

}
