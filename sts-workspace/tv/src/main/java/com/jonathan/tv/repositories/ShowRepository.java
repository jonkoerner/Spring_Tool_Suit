package com.jonathan.tv.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jonathan.tv.models.Show;

public interface ShowRepository extends CrudRepository<Show, Long> {

}
