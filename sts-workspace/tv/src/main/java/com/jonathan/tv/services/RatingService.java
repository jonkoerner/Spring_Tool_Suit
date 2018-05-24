package com.jonathan.tv.services;

import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.jonathan.tv.models.Rating;
import com.jonathan.tv.models.User;
import com.jonathan.tv.repositories.RatingRepository;
import com.jonathan.tv.repositories.UserRepository;


@Service
public class RatingService {
	RatingRepository rR;
	public RatingService(RatingRepository rR) {
		this.rR = rR;
	}
	
	public ArrayList<Rating> all(){
    	return (ArrayList<Rating>)rR.findAll();
    }
//		 
//	public Optional<Rating> find(Long id) {
//		return rR.findById(id);
//	}
//	
//	public void update(Rating rating) {
//		rR.save(rating);
//	}
//	
//	public void destroy(Long id) {
//		rR.deleteById(id);
//	}
//	
}