package tech.readresolve.skilltree.repositories;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import tech.readresolve.skilltree.dtos.out.CertificationLevelLabelValue;
import tech.readresolve.skilltree.entities.CertificationLevel;

@Repository
public interface CertificationLevelRepository
	extends BaseRepository<CertificationLevel> {

    Collection<CertificationLevelLabelValue> findAllProjectedByOrderByEuropeanLevel();

}
