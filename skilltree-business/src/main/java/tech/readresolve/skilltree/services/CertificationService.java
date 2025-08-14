package tech.readresolve.skilltree.services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.readresolve.skilltree.dtos.in.CertificationCreate;
import tech.readresolve.skilltree.dtos.out.ActivityLabelValue;
import tech.readresolve.skilltree.dtos.out.CertificationLabelValue;
import tech.readresolve.skilltree.entities.Certification;
import tech.readresolve.skilltree.entities.CertificationLevel;
import tech.readresolve.skilltree.repositories.ActivityReposiroty;
import tech.readresolve.skilltree.repositories.CertificationLevelRepository;
import tech.readresolve.skilltree.repositories.CertificationReposiroty;

@Service
public class CertificationService extends BaseService {

    private final CertificationReposiroty certifications;

    private final CertificationLevelRepository levels;

    private final ActivityReposiroty activities;

    CertificationService(CertificationReposiroty certifications,
	    CertificationLevelRepository levels,
	    ActivityReposiroty activities) {
	this.certifications = certifications;
	this.levels = levels;
	this.activities = activities;
    }

    @Transactional
    public void create(CertificationCreate inputs) {
	Certification entity = new Certification();
	CertificationLevel level = levels
		.getReferenceById(inputs.certificationLevelId());
	entity.setLevel(level);
	entity.setCode(inputs.code());
	entity.setName(inputs.name());
	entity.setAcronym(inputs.acronym());
	entity.setStartYear(inputs.startYear());
	entity.setDescription(inputs.description());
	certifications.save(entity);
    }

    public Collection<CertificationLabelValue> labelValues() {
	return certifications.findAllProjectedByOrderByNameAscCode();
    }

    public Collection<ActivityLabelValue> activitiesLabelValues(
	    Long certificationId) {
	existsByIdOrNotFound(certifications, certificationId);
	return activities
		.findAllProjectedByCertificationIdOrderByCode(certificationId);
    }

}
