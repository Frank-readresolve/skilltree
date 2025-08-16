package tech.readresolve.skilltree.dtos.in;

import jakarta.validation.constraints.NotBlank;
import tech.readresolve.skilltree.misc.ExcludeFromJacocoGeneratedReport;

public record SignIn(@NotBlank String username, @NotBlank String password) {

    @Override
    @ExcludeFromJacocoGeneratedReport
    public String toString() {
	// DO NOT OUTPUT password!
	return String.format("{username=%s, [PROTECTED]}", username);
    }

}
