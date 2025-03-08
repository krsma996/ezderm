package com.ezderm.solution.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezderm.solution.dao.custom.AppointmentDaoCustom;
import com.ezderm.solution.model.Appointment;

public interface AppointmentDao extends JpaRepository<Appointment, Long>,AppointmentDaoCustom {

}
