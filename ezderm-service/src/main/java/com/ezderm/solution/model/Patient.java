package com.ezderm.solution.model;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String firstName;
	private String middleName;
	private String lastName;
	private LocalDate dateOfBirth;
	@OneToMany(mappedBy = "patient")
	private Set<Appointment> appointments;
}
