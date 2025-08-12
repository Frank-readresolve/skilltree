package tech.readresolve.skilltree.controllers;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tech.readresolve.skilltree.dtos.in.AccountCreate;
import tech.readresolve.skilltree.dtos.in.SignIn;
import tech.readresolve.skilltree.dtos.out.AccountView;
import tech.readresolve.skilltree.dtos.out.AuthInfo;
import tech.readresolve.skilltree.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	private final AccountService service;

	AccountController(AccountService service) {
		this.service = service;
	}

	@PostMapping("/sign-in")
	AuthInfo signIn(@RequestBody @Valid SignIn inputs) {
		return service.signIn(inputs);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void create(@RequestBody @Valid AccountCreate inputs) {
		service.create(inputs);
	}

	@PatchMapping("/{id}/password")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void resetPassword(@PathVariable("id") Long accountId) {
		service.resetPassword(accountId);
	}

	@GetMapping
	Collection<AccountView> views() {
		return service.views();
	}

}
