package tech.readresolve.skilltree.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.readresolve.skilltree.dtos.out.CertificationLevelLabelValue;
import tech.readresolve.skilltree.services.CertificationLevelService;

@RestController
@RequestMapping("/certification-levels")
public class CertificationLevelController {

	private final CertificationLevelService service;

	CertificationLevelController(CertificationLevelService service) {
		this.service = service;
	}

	@GetMapping("/label-values")
	Collection<CertificationLevelLabelValue> labelValues() {
		return service.labelValues();
	}

}
