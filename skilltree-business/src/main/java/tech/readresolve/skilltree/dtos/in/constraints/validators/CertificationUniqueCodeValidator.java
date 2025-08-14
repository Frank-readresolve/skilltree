package tech.readresolve.skilltree.dtos.in.constraints.validators;

import jakarta.validation.ConstraintValidatorContext;

import tech.readresolve.skilltree.dtos.in.constraints.CertificationUniqueCode;
import tech.readresolve.skilltree.repositories.CertificationReposiroty;

public final class CertificationUniqueCodeValidator
	extends BaseConstraintValidator<CertificationUniqueCode, String> {

    private final CertificationReposiroty certifications;

    CertificationUniqueCodeValidator(CertificationReposiroty certifications) {
	this.certifications = certifications;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
	if (value == null) {
	    return true;
	}
	return !certifications.existsByCodeIgnoreCase(value);
    }

}
