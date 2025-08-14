package tech.readresolve.skilltree.dtos.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import tech.readresolve.skilltree.dtos.in.constraints.SkillUniqueCode;

public record SkillCreate(@NotNull Long activityId,
	@NotBlank @Size(min = 1, max = 15) @SkillUniqueCode String code,
	@NotBlank @Size(min = 1, max = 150) String name,
	@Size(min = 1, max = 5000) String description) {
    //
}
