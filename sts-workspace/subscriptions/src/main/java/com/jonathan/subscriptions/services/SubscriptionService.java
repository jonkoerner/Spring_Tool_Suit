package com.jonathan.subscriptions.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.jonathan.subscriptions.models.Subscription;
import com.jonathan.subscriptions.repositories.SubscriptionRepository;

@Service
public class SubscriptionService {
	SubscriptionRepository sR;
	public SubscriptionService(SubscriptionRepository sR) {
		this.sR= sR;
	}
	public ArrayList<Subscription> all(){
		return (ArrayList<Subscription>) sR.findAll();
	}
	
}
