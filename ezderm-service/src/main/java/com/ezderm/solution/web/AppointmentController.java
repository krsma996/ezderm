package com.ezderm.solution.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ezderm.solution.dto.AppointmentDto;
import com.ezderm.solution.model.Appointment;
import com.ezderm.solution.service.AppointmentService;
import com.ezderm.solution.web.api.AppointmentApi;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AppointmentController implements AppointmentApi {
	
	private final AppointmentService appointmentService;
	
	@Override
	public ResponseEntity<AppointmentDto> getAppointmentById(Long id, String userName) {
		AppointmentDto appointmentsWithDoctors = this.appointmentService.getById(id,userName);
		return ResponseEntity.ok(appointmentsWithDoctors);
	}

	@Override
	public ResponseEntity<AppointmentDto> createAppointment(AppointmentDto appointmentDto, String userName) {
		AppointmentDto appointment = this.appointmentService.createAppointment(appointmentDto, userName);
		return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
	}

}
