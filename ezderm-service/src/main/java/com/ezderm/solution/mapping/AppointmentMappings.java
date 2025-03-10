package com.ezderm.solution.mapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ezderm.solution.dto.AppointmentDto;
import com.ezderm.solution.dto.DoctorDto;
import com.ezderm.solution.dto.PatientDto;
import com.ezderm.solution.exception.ResourceNotFoundException;
import com.ezderm.solution.model.Appointment;
import com.ezderm.solution.model.Doctor;
import com.ezderm.solution.model.Patient;

public class AppointmentMappings {
	
	
	public static AppointmentDto mapToAppointmentDto(Appointment appointment,String headerUsername) {
		AppointmentDto dto = new AppointmentDto();
        dto.setId(appointment.getId());
        dto.setUuid(appointment.getUuid());
        dto.setDate(appointment.getDate());
        dto.setStatus(appointment.getStatus());

        Patient patient = appointment.getPatient();
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patient.getId());
        patientDto.setUuid(patient.getUuid());
        patientDto.setFirstName(patient.getFirstName());
        patientDto.setMiddleName(patient.getMiddleName());
        patientDto.setLastName(patient.getLastName());
        patientDto.setDateOfBirth(patient.getDateOfBirth());
        dto.setPatientData(patientDto);
        Set<DoctorDto> doctorDtos = appointment.getDoctors().stream()
                .map(doctor -> {
                    DoctorDto doctorDto = new DoctorDto();
                    doctorDto.setId(doctor.getId());
                    doctorDto.setUuid(doctor.getUuid());
                    doctorDto.setUserName(headerUsername);
                    doctorDto.setFirstName(doctor.getFirstName());
                    doctorDto.setLastName(doctor.getLastName());
                    return doctorDto;
                })
                .collect(Collectors.toSet());
        dto.setDoctors(doctorDtos);
        return dto;
	}
	public static Set<Doctor> mapDoctors(AppointmentDto appointmentDto,List<Doctor> allDoctors) {
	    return appointmentDto.getDoctors().stream()
	            .map(doctorDto -> {
	                return allDoctors.stream()
	                        .filter(doctor -> doctor.getId().equals(doctorDto.getId()))
	                        .findFirst()
	                        .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + doctorDto.getId()));
	            })
	            .collect(Collectors.toSet());
	}

}
