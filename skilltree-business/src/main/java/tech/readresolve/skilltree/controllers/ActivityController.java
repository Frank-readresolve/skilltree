package tech.readresolve.skilltree.controllers;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tech.readresolve.skilltree.dtos.in.ActivityCreate;
import tech.readresolve.skilltree.services.ActivityService;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService service;

    ActivityController(ActivityService service) {
	this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void create(@RequestBody @Valid ActivityCreate inputs) {
	service.create(inputs);
    }

}
