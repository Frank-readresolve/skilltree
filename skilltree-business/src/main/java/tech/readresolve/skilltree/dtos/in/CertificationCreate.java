package tech.readresolve.skilltree.dtos.in;

import java.time.Year;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import tech.readresolve.skilltree.dtos.in.constraints.CertificationUniqueCode;
import tech.readresolve.skilltree.dtos.in.constraints.CertificationYear;

public record CertificationCreate(@NotNull Long certificationLevelId,
	@NotBlank @Size(min = 1, max = 10) @CertificationUniqueCode String code,
	@NotBlank @Size(min = 1, max = 50) String name,
	@NotBlank @Size(min = 2, max = 4) String acronym,
	@NotNull @CertificationYear Year startYear,
	@Size(min = 1, max = 5000) String description) {
    //
}
