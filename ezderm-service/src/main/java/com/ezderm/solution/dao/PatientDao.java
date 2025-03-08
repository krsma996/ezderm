package com.ezderm.solution.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezderm.solution.dao.custom.PatinentDaoCustom;
import com.ezderm.solution.model.Patient;

public interface PatientDao extends JpaRepository<Patient, Long> ,PatinentDaoCustom{

}
