package tech.readresolve.skilltree.dtos.in;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import tech.readresolve.skilltree.dtos.in.constraints.AccountUniqueUsername;

public record AccountCreate(
	@NotBlank @Size(min = 6, max = 255) @Email @AccountUniqueUsername String username,
	@NotBlank @Size(min = 1, max = 100) String firstname,
	@NotBlank @Size(min = 1, max = 100) String lastname) {
    //
}
