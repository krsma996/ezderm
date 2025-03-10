package com.ezderm.solution.service;

import com.ezderm.solution.dto.DoctorDto;

public interface DoctorService {
	
	
	DoctorDto saveDoctor(DoctorDto doctorDto,String userName);
	
	DoctorDto deleteDoctor(String uuid,String userName);

}
