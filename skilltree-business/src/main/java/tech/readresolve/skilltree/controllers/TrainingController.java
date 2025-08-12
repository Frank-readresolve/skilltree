package tech.readresolve.skilltree.controllers;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tech.readresolve.skilltree.dtos.in.TrainingCreate;
import tech.readresolve.skilltree.services.TrainingService;

@RestController
@RequestMapping("/trainings")
public class TrainingController {

	private final TrainingService service;

	TrainingController(TrainingService service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void create(@RequestBody @Valid TrainingCreate inputs) {
		service.create(inputs);
	}

}
