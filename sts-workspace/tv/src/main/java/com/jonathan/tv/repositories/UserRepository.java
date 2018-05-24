package com.jonathan.tv.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jonathan.tv.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
User findByUsername(String username);
}
