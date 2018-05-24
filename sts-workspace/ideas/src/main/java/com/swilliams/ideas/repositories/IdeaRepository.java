package com.swilliams.ideas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.swilliams.ideas.models.Idea;

@Repository
public interface IdeaRepository extends CrudRepository<Idea,Long>{

}
