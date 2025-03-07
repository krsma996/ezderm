package com.ezderm.solution.model;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(unique = true, nullable = false)
	private String username;
	private String firstName;
	private String lastName;
	@ManyToMany(mappedBy = "doctors")
	private Set<Appointment> appointments;
}
