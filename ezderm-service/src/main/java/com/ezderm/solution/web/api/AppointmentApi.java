package com.ezderm.solution.web.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ezderm.solution.dto.AppointmentDto;
import com.ezderm.solution.errors.RestErrors;
import com.ezderm.solution.utils.ApplicationConstants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = ApplicationConstants.APPOINTMENT_API_TAG, description = "The API for managing Appointment entities")
public interface AppointmentApi {

	@Operation(summary = "Retrive appointment by ID", description = "Retrieve appointment by ID with list of doctors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AppointmentDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input or parameters", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "401", description = "Unathorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),   
            @ApiResponse(responseCode = "404", description = "Resource not found, invalid key", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "500", description = "Internal error, something went wrong", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))) })
    @GetMapping(value = "/appointment/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<AppointmentDto> getAppointmentById(@Parameter(in = ParameterIn.PATH, description = "Appointment ID", required = true) 
												      @PathVariable("id") Long id,@RequestHeader("X-Username") String userName);

	@Operation(summary = "Create appointment", description = "Create appointment in local DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AppointmentDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input or parameters", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "401", description = "Unathorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),   
            @ApiResponse(responseCode = "404", description = "Resource not found, invalid key", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "500", description = "Internal error, something went wrong", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))) })
    @PostMapping(value = "/appointment", produces = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<AppointmentDto> createAppointment(@Valid @RequestBody(required = true) AppointmentDto appointmentDto,
												@RequestHeader("X-Username") String userName);

	
}
