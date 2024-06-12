package tech.readresolve.skilltree.dtos.in;

import jakarta.validation.constraints.NotBlank;

public record SignIn(@NotBlank String username, @NotBlank String password) {

    @Override
    public String toString() {
	return String.format("{username=%s, password=[PROTECTED]}", username);
    }

}
