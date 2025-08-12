package tech.readresolve.skilltree.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.readresolve.skilltree.entities.Trainer;

@Repository
public interface TrainerRepository extends BaseRepository<Trainer> {

	@Query(value = "SELECT concat('FO', to_char(nextval('trainers_code_seq'), 'FM00000000'))", nativeQuery = true)
	String nextTrainerCode();

}
