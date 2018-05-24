package com.jonathan.subscriptions.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.jonathan.subscriptions.models.Subscription;


public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
	ArrayList<Subscription> findAll();
}
