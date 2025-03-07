package com.ezderm.solution.model;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String date;
	private String time;
	private String status;

	@ManyToMany
	@JoinTable(name = "appointment_doctor", 
			   joinColumns = @JoinColumn(name = "appointment_id"), 
			   inverseJoinColumns = @JoinColumn(name = "doctor_id"))
	private Set<Doctor> doctors;

	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;
}
