package com.ezderm.solution.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false,onlyExplicitlyIncluded = true)
@ToString(callSuper = false)
@Schema(description = "Represents a doctor object")
@AllArgsConstructor
public class AppointmentDto {

}
