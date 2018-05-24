package com.jonathan.subscriptions.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jonathan.subscriptions.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
}
