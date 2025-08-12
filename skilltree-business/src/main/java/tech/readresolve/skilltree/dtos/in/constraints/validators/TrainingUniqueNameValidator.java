package tech.readresolve.skilltree.dtos.in.constraints.validators;

import jakarta.validation.ConstraintValidatorContext;

import tech.readresolve.skilltree.dtos.in.constraints.TrainingUniqueName;
import tech.readresolve.skilltree.repositories.TrainingRepository;

public final class TrainingUniqueNameValidator
		extends BaseConstraintValidator<TrainingUniqueName, String> {

	private final TrainingRepository trainings;

	TrainingUniqueNameValidator(TrainingRepository trainings) {
		this.trainings = trainings;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return !trainings.existsByNameIgnoreCase(value);
	}

}
