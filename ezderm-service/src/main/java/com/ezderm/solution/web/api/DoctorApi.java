package com.ezderm.solution.web.api;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ezderm.solution.dto.DoctorDto;
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



@Tag(name = ApplicationConstants.DOCTOR_API_TAG, description = "The API for managing Doctor entities")
public interface DoctorApi {

	
	@Operation(summary = "Create doctor", description = "Create doctor in local DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = DoctorDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input or parameters", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "401", description = "Unathorized", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),   
            @ApiResponse(responseCode = "404", description = "Resource not found, invalid key", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "500", description = "Internal error, something went wrong", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))) })
    @PostMapping(value = "/doctor", produces = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<DoctorDto> createDoctor(@Valid @RequestBody(required = true) DoctorDto doctorDto,@RequestHeader("X-Username") String userName);


	@Operation(summary = "Delete doctor", description = "Delete doctor in local DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctor record deleted successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = DoctorDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input or parameters", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "401", description = "Username header missing", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "403", description = "User not authorized to delete this doctor record", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "404", description = "Doctor not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "500", description = "Internal error, something went wrong", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))) })
    @DeleteMapping(value = "/doctor/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<DoctorDto> deleteDoctor(@Parameter(in = ParameterIn.PATH, description = "Doctor ID", required = true) @PathVariable("id") String uuid,@RequestHeader("X-Username") String userName);


}
