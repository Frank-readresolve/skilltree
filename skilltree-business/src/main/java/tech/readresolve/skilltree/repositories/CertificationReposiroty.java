package tech.readresolve.skilltree.repositories;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import tech.readresolve.skilltree.dtos.out.CertificationLabelValue;
import tech.readresolve.skilltree.entities.Certification;

@Repository
public interface CertificationReposiroty extends BaseRepository<Certification> {

	boolean existsByCodeIgnoreCase(String value);

	Collection<CertificationLabelValue> findAllProjectedByOrderByNameAscCode();

}
