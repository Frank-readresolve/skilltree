package tech.readresolve.skilltree.dtos.in.constraints.validators;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidatorContext;
import tech.readresolve.skilltree.dtos.in.TrainingCreate;
import tech.readresolve.skilltree.dtos.in.constraints.TrainingDatesConsistent;

public final class TrainingDatesConsistentValidator extends
	BaseConstraintValidator<TrainingDatesConsistent, TrainingCreate> {

    @Override
    public boolean isValid(TrainingCreate value,
	    ConstraintValidatorContext context) {
	LocalDate startDate = value.startDate();
	LocalDate endDate = value.endDate();
	if (startDate == null || endDate == null) { // Not comparable
	    return false;
	}
	return endDate.isAfter(startDate);
    }

}
