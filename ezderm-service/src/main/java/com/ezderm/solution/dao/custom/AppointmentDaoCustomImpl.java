package com.ezderm.solution.dao.custom;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ezderm.solution.model.Appointment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class AppointmentDaoCustomImpl implements AppointmentDaoCustom{

	private final EntityManager entityManager;
	
	
	@Override
	public Optional<Appointment> findAppointmentWithDoctorsAndPatientById(Long id) {
	    CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery<Appointment> query = cb.createQuery(Appointment.class);
	    Root<Appointment> appointment = query.from(Appointment.class);
	    appointment.fetch("doctors", JoinType.LEFT);
	    appointment.fetch("patient", JoinType.LEFT);
	    query.select(appointment).where(cb.equal(appointment.get("id"), id));
	    try {
	        Appointment result = entityManager.createQuery(query).getSingleResult();
	        return Optional.of(result);
	    } catch (NoResultException e) {
	        return Optional.empty();
	    } catch (Exception e) {
	        throw new RuntimeException("Error fetching appointment with doctors and patient", e);
	    }
	}
	
	
	
}
