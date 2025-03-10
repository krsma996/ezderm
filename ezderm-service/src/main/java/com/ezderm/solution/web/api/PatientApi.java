package com.ezderm.solution.web.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ezderm.solution.dto.PatientDto;
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

@Tag(name = ApplicationConstants.PATIENT_API_TAG, description = "The API for managing Patient entities")
public interface PatientApi {
	
	
	@Operation(summary = "Create Patient", description = "Create Patient in local DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PatientDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input or parameters", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found, invalid key", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "500", description = "Internal error, something went wrong", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))) })
    @PostMapping(value = "/patient", produces = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<PatientDto> createPatient(@Valid @RequestBody(required = true) PatientDto patientDto,@RequestHeader("X-Username") String userName);
	
	
	@Operation(summary = "Delete Patient", description = "Delete Patient in local DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PatientDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input or parameters", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found, invalid key", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "500", description = "Internal error, something went wrong", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))) })
    @DeleteMapping(value = "/patient/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<Void> deletePatient(@Parameter(in = ParameterIn.PATH, description = "Patient ID", required = true) @PathVariable("id") Long id);

}
