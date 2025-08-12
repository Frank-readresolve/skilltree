package tech.readresolve.skilltree.dtos.in;

import jakarta.validation.constraints.NotBlank;

public record SignIn(@NotBlank String username, @NotBlank String password) {

	@Override
	public String toString() {
		// DO NOT OUTPUT password!
		return String.format("{username=%s, [PROTECTED]}", username);
	}

}
