package tech.readresolve.skilltree.dtos.in;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import tech.readresolve.skilltree.dtos.in.constraints.TrainingDatesConsistent;
import tech.readresolve.skilltree.dtos.in.constraints.TrainingUniqueName;

@TrainingDatesConsistent
public record TrainingCreate(@NotNull Long certificationId,
	@NotBlank @Size(min = 1, max = 150) @TrainingUniqueName String name,
	@NotNull LocalDate startDate, @NotNull LocalDate endDate,
	@NotBlank @Size(min = 1, max = 2000) String description) {
}
