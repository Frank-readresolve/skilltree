package tech.readresolve.skilltree.services;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import tech.readresolve.skilltree.config.SecurityHelper;
import tech.readresolve.skilltree.controllers.errors.ResourceNotFoundException;
import tech.readresolve.skilltree.entities.BaseEntity;
import tech.readresolve.skilltree.repositories.BaseRepository;

@Transactional(readOnly = true)
abstract class BaseService {

	private static final String NOT_FOUND_MSG = "resource not found with id '%s'";

	@Autowired
	private SecurityHelper security;

	BaseService() {
		//
	}

	protected final SecurityHelper security() {
		return security;
	}

	protected static <T extends BaseEntity> void existsByIdOrNotFound(
			BaseRepository<T> repo, Long id) throws ResourceNotFoundException {
		if (!repo.existsById(id)) {
			throw new ResourceNotFoundException(
					String.format(NOT_FOUND_MSG, id));
		}
	}

	protected static <T extends BaseEntity> T findByIdOrNotFound(
			BaseRepository<T> repo, Long id) throws ResourceNotFoundException {
		return findOrNotFound(() -> repo.findById(id), NOT_FOUND_MSG, id);
	}

	protected static <T> T findOrNotFound(Supplier<Optional<T>> supplier,
			String message, Object... specifiers)
			throws ResourceNotFoundException {
		return supplier.get().orElseThrow(() -> new ResourceNotFoundException(
				String.format(message, specifiers)));
	}

}
