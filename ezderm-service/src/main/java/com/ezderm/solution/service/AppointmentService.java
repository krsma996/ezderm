package com.ezderm.solution.service;

import com.ezderm.solution.dto.AppointmentDto;

public interface AppointmentService {

	AppointmentDto getById(Long id,String userName);
	
	AppointmentDto createAppointment(AppointmentDto appDto,String userName);
}
