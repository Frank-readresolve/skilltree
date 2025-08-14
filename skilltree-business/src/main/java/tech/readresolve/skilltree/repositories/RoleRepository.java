package tech.readresolve.skilltree.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import tech.readresolve.skilltree.entities.Role;

@Repository
public interface RoleRepository extends BaseRepository<Role> {

    Optional<Role> findByCode(String value);

}
