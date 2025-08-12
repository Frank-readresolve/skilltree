package tech.readresolve.skilltree.dtos.in.constraints.validators;

import jakarta.validation.ConstraintValidatorContext;

import tech.readresolve.skilltree.dtos.in.constraints.AccountUniqueUsername;
import tech.readresolve.skilltree.repositories.AccountReposiroty;

public final class AccountUniqueUsernameValidator
		extends BaseConstraintValidator<AccountUniqueUsername, String> {

	private final AccountReposiroty accounts;

	AccountUniqueUsernameValidator(AccountReposiroty accounts) {
		this.accounts = accounts;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return !accounts.existsByUsernameIgnoreCase(value);
	}

}
