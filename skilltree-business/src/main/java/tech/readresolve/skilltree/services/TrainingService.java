package tech.readresolve.skilltree.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.readresolve.skilltree.dtos.in.TrainingCreate;
import tech.readresolve.skilltree.entities.Certification;
import tech.readresolve.skilltree.entities.Training;
import tech.readresolve.skilltree.repositories.CertificationReposiroty;
import tech.readresolve.skilltree.repositories.TrainingRepository;

@Service
public class TrainingService extends BaseService {

	private final TrainingRepository trainings;

	private final CertificationReposiroty certifications;

	TrainingService(TrainingRepository trainings,
			CertificationReposiroty certifications) {
		this.trainings = trainings;
		this.certifications = certifications;
	}

	@Transactional
	public void create(TrainingCreate inputs) {
		Training entity = new Training();
		Certification certification = certifications
				.getReferenceById(inputs.certificationId());
		entity.setCertification(certification);
		entity.setName(inputs.name());
		entity.setStartDate(inputs.startDate());
		entity.setEndDate(inputs.endDate());
		entity.setDescription(inputs.description());
		trainings.save(entity);
	}

}
