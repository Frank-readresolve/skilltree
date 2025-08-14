package tech.readresolve.skilltree.controllers;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tech.readresolve.skilltree.dtos.in.SkillCreate;
import tech.readresolve.skilltree.services.SkillService;

@RestController
@RequestMapping("/skills")
public class SkillController {

    private final SkillService service;

    SkillController(SkillService service) {
	this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void create(@RequestBody @Valid SkillCreate inputs) {
	service.create(inputs);
    }

}
