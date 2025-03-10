package com.ezderm.solution.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezderm.solution.dao.custom.DoctorDaoCustom;
import com.ezderm.solution.model.Doctor;

public interface DoctorDao extends JpaRepository<Doctor, Long>,DoctorDaoCustom {
	
	boolean existsByUsername(String username);
	
	Doctor findByUuid(String uuid);
	
	Doctor findByUsername(String username);

}
