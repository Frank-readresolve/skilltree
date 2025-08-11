package tech.readresolve.skilltree.dtos.in.constraints.validators;

import java.time.Year;

import jakarta.validation.ConstraintValidatorContext;
import tech.readresolve.skilltree.dtos.in.constraints.CertificationYear;

public final class CertificationYearValidator
	extends BaseConstraintValidator<CertificationYear, Year> {

    private static final Year FLOOR = Year.of(2018);

    @Override
    public boolean isValid(Year value, ConstraintValidatorContext context) {
	if (value == null) {
	    return true;
	}
	Year year = Year.now();
	return !value.isBefore(FLOOR) && !value.isAfter(year);
    }

}
