package com.ezderm.solution.web.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.ezderm.solution.dto.DoctorDto;
import com.ezderm.solution.errors.RestErrors;
import com.ezderm.solution.utils.ApplicationConstants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;



@Tag(name = ApplicationConstants.DOCTOR_API_TAG, description = "The API for managing Doctor entities")
public interface DoctorApi {

	
	@Operation(summary = "Create user", description = "Ceate user in Keycloak and in local DB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = DoctorDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input or parameters", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "404", description = "Resource not found, invalid key", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))),
            @ApiResponse(responseCode = "500", description = "Internal error, something went wrong", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestErrors.class))) })
    @PostMapping(value = "/doctor", produces = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<DoctorDto> createDoctor(
			@Parameter(in = ParameterIn.DEFAULT,description = "Create Doctor",required = false)@RequestBody(required = true) DoctorDto doctorDto);
}
