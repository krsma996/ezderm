package com.ezderm.solution.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ezderm.solution.dao.AppointmentDao;
import com.ezderm.solution.dao.DoctorDao;
import com.ezderm.solution.dao.PatientDao;
import com.ezderm.solution.dto.AppointmentDto;
import com.ezderm.solution.exception.ResourceNotFoundException;
import com.ezderm.solution.mapping.AppointmentMappings;
import com.ezderm.solution.model.Appointment;
import com.ezderm.solution.model.Doctor;
import com.ezderm.solution.model.Patient;
import com.ezderm.solution.service.AppointmentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

	private final AppointmentDao appointmentDao;
    private final DoctorDao doctorDao;
    private final PatientDao patientDao;

	@Override
	@Transactional
	public AppointmentDto getById(Long id,String userName) {
		 Optional<Appointment> appointmentOpt = appointmentDao.findAppointmentWithDoctorsAndPatientById(id);
		 Appointment appointment = null;
		 if(appointmentOpt.isPresent()) {
			 appointment = appointmentOpt.get();
		 }else {
			 return null;
		 }
		return AppointmentMappings.mapToAppointmentDto(appointment,userName);
	}

	@Override
	public AppointmentDto createAppointment(AppointmentDto appointmentDto, String userName) {		
		Doctor doctor = this.doctorDao.findByUsername(userName);
		if(doctor == null) {
			throw new ResourceNotFoundException("Doctor with " + userName + " Not Founded ");
		}	
		Patient patient = this.patientDao.findById(appointmentDto.getPatientData().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + appointmentDto.getPatientData().getId()));	
		List<Doctor> allDoctors = this.doctorDao.findAll();
		Appointment appointment = new Appointment();
        appointment.setDate(appointmentDto.getDate());
        appointment.setStatus(appointmentDto.getStatus());     
        Set<Doctor> doctors = AppointmentMappings.mapDoctors(appointmentDto,allDoctors);
        appointment.setDoctors(doctors);
        appointment.setPatient(patient); 
        appointment.setUuid(appointmentDto.getUuid());
        this.appointmentDao.save(appointment);
		return appointmentDto;
	}


	


	
	
}
