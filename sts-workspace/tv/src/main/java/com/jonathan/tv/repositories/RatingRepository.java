package com.jonathan.tv.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jonathan.tv.models.Rating;

public interface RatingRepository extends CrudRepository<Rating, Integer> {

}
