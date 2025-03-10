package com.ezderm.solution.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.ezderm.solution.utils.StatusAppointment;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class AppointmentDto {
    @NotNull(message = "ID is required")
    @Schema(requiredMode = RequiredMode.REQUIRED, description = "ID of appointment")
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "UUID is required")
    @Schema(requiredMode = RequiredMode.REQUIRED, description = "UUID of appointment")
    private String uuid;

    @NotNull(message = "Date and time are required")
    @Schema(requiredMode = RequiredMode.REQUIRED, description = "Date Time of AppointMent")
    private LocalDateTime date;

    @NotNull(message = "Status is required")
    @Schema(requiredMode = RequiredMode.REQUIRED, description = "Status of employment")
    private StatusAppointment status;

    @NotNull(message = "Patient data is required")
    @Schema(requiredMode = RequiredMode.REQUIRED, description = "Patient Data")
    private PatientDto patientData;

    @NotNull(message = "Doctors are required")
    @Schema(requiredMode = RequiredMode.REQUIRED, description = "List of doctors .")
    private Set<DoctorDto> doctors;
}