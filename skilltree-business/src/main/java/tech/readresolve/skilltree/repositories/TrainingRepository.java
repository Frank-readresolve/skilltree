package tech.readresolve.skilltree.repositories;

import org.springframework.stereotype.Repository;

import tech.readresolve.skilltree.entities.Training;

@Repository
public interface TrainingRepository extends BaseRepository<Training> {

    boolean existsByNameIgnoreCase(String value);

}
