package com.ezderm.solution.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ezderm.solution.dto.DoctorDto;
import com.ezderm.solution.service.DoctorService;
import com.ezderm.solution.web.api.DoctorApi;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DoctorController implements DoctorApi {
	
	private final DoctorService doctorService;
	

	@Override
	public ResponseEntity<DoctorDto> createDoctor(DoctorDto doctorDto) {
		DoctorDto savedDoctor = this.doctorService.saveDoctor(doctorDto);
		return ResponseEntity.ok(savedDoctor);
	}

}
