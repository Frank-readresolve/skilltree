package tech.readresolve.skilltree.repositories;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import tech.readresolve.skilltree.dtos.out.ActivityLabelValue;
import tech.readresolve.skilltree.entities.Activity;

@Repository
public interface ActivityReposiroty extends BaseRepository<Activity> {

	boolean existsByCodeIgnoreCase(String value);

	Collection<ActivityLabelValue> findAllProjectedByCertificationIdOrderByCode(
			Long certificationId);

}
