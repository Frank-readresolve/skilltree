package tech.readresolve.skilltree.controllers;

import java.util.Collection;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tech.readresolve.skilltree.dtos.in.CertificationCreate;
import tech.readresolve.skilltree.dtos.out.ActivityLabelValue;
import tech.readresolve.skilltree.dtos.out.CertificationLabelValue;
import tech.readresolve.skilltree.services.CertificationService;

@RestController
@RequestMapping("/certifications")
public class CertificationController {

    private final CertificationService service;

    CertificationController(CertificationService service) {
	this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void create(@RequestBody @Valid CertificationCreate inputs) {
	service.create(inputs);
    }

    @GetMapping("/label-values")
    Collection<CertificationLabelValue> labelValues() {
	return service.labelValues();
    }

    @GetMapping("/{id}/activities/label-values")
    Collection<ActivityLabelValue> activitiesLabelValues(
	    @PathVariable("id") Long certificationId) {
	return service.activitiesLabelValues(certificationId);
    }

}
