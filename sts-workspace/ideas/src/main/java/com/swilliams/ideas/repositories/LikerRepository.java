package com.swilliams.ideas.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.swilliams.ideas.models.Liker;

public interface LikerRepository extends CrudRepository<Liker,Long>{
	@Modifying
	@Transactional
	@Query(value="DELETE FROM liker WHERE idea_id = ?1 AND user_id = ?2", nativeQuery=true)
	public void deleteFrom(Long idea_id,Long user_id);
}
