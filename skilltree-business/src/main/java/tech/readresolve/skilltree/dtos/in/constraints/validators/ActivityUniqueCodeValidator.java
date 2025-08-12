package tech.readresolve.skilltree.dtos.in.constraints.validators;

import jakarta.validation.ConstraintValidatorContext;

import tech.readresolve.skilltree.dtos.in.constraints.ActivityUniqueCode;
import tech.readresolve.skilltree.repositories.ActivityReposiroty;

public final class ActivityUniqueCodeValidator
		extends BaseConstraintValidator<ActivityUniqueCode, String> {

	private final ActivityReposiroty activities;

	ActivityUniqueCodeValidator(ActivityReposiroty activities) {
		this.activities = activities;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return !activities.existsByCodeIgnoreCase(value);
	}

}
