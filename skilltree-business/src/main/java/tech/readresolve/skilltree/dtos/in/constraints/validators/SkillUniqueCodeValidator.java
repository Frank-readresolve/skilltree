package tech.readresolve.skilltree.dtos.in.constraints.validators;

import jakarta.validation.ConstraintValidatorContext;

import tech.readresolve.skilltree.dtos.in.constraints.SkillUniqueCode;
import tech.readresolve.skilltree.repositories.SkillReposiroty;

public final class SkillUniqueCodeValidator
	extends BaseConstraintValidator<SkillUniqueCode, String> {

    private final SkillReposiroty skills;

    SkillUniqueCodeValidator(SkillReposiroty skills) {
	this.skills = skills;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
	if (value == null) {
	    return true;
	}
	return !skills.existsByCodeIgnoreCase(value);
    }

}
