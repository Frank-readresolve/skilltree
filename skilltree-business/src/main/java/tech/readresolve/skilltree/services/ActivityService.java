package tech.readresolve.skilltree.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.readresolve.skilltree.dtos.in.ActivityCreate;
import tech.readresolve.skilltree.entities.Activity;
import tech.readresolve.skilltree.entities.Certification;
import tech.readresolve.skilltree.repositories.ActivityReposiroty;
import tech.readresolve.skilltree.repositories.CertificationReposiroty;

@Service
public class ActivityService extends BaseService {

    private final ActivityReposiroty activities;

    private final CertificationReposiroty certifications;

    ActivityService(ActivityReposiroty activities,
	    CertificationReposiroty certifications) {
	this.activities = activities;
	this.certifications = certifications;
    }

    @Transactional
    public void create(ActivityCreate inputs) {
	Activity entity = new Activity();
	Certification certification = certifications
		.getReferenceById(inputs.certificationId());
	entity.setCertification(certification);
	entity.setCode(inputs.code());
	entity.setName(inputs.name());
	entity.setDescription(inputs.description());
	activities.save(entity);
    }

}
