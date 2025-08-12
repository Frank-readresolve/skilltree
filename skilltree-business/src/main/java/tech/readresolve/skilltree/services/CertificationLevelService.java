package tech.readresolve.skilltree.services;

import java.util.Collection;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import tech.readresolve.skilltree.dtos.out.CertificationLevelLabelValue;
import tech.readresolve.skilltree.repositories.CertificationLevelRepository;

@Service
public class CertificationLevelService extends BaseService {

	private final CertificationLevelRepository levels;

	CertificationLevelService(CertificationLevelRepository levels) {
		this.levels = levels;
	}

	@Cacheable("default-cache")
	public Collection<CertificationLevelLabelValue> labelValues() {
		return levels.findAllProjectedByOrderByEuropeanLevel();
	}

}
