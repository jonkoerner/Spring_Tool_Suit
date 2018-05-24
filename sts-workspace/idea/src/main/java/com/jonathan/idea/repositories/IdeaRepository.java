package com.jonathan.idea.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jonathan.idea.models.Idea;

public interface IdeaRepository extends CrudRepository<Idea, Long> {
	
}
