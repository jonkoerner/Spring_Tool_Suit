package com.jonathan.beltexam.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jonathan.beltexam.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
User findByEmail(String email);
}
