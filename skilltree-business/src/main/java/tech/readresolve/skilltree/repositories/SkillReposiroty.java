package tech.readresolve.skilltree.repositories;

import org.springframework.stereotype.Repository;

import tech.readresolve.skilltree.entities.Skill;

@Repository
public interface SkillReposiroty extends BaseRepository<Skill> {

    boolean existsByCodeIgnoreCase(String value);

}
