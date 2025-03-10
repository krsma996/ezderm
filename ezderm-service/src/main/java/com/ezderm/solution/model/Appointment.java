package com.ezderm.solution.model;

import java.time.LocalDateTime;
import java.util.Set;

import com.ezderm.solution.utils.StatusAppointment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(unique = true, nullable = false, name="UUID", updatable = false)
	private String uuid;
	@Column(name = "DATE_TIME")
	private LocalDateTime date;
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private StatusAppointment status;

	@ManyToMany
	@JoinTable(name = "appointment_doctor", 
			   joinColumns = @JoinColumn(name = "appointment_id"), 
			   inverseJoinColumns = @JoinColumn(name = "doctor_id"))
	private Set<Doctor> doctors;

	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;
}
