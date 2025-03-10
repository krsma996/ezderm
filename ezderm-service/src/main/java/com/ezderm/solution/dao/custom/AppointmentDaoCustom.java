package com.ezderm.solution.dao.custom;

import java.util.Optional;

import com.ezderm.solution.model.Appointment;

public interface AppointmentDaoCustom {

	Optional<Appointment> findAppointmentWithDoctorsAndPatientById(Long id);

}
