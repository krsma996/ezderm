package com.ezderm.solution.service;

import com.ezderm.solution.dto.DoctorDto;

public interface DoctorService {
	
	
	DoctorDto saveDoctor(DoctorDto doctorDto);
	
	void deleteDoctor(Long doctorId);

}
