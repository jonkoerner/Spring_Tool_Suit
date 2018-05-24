package com.jonathan.idea.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jonathan.idea.models.Liker;
import com.jonathan.idea.repositories.LikerRepository;
	
	@Service
	public class LikerService {
	private LikerRepository lR;
		
		public LikerService(LikerRepository lR) {
			this.lR = lR;
		}
		
		public void deletefrom(Long idea_id,Long user_id) {
			lR.deleteFrom(idea_id, user_id);
		}
		
		public void create(Liker liker) {
			lR.save(liker);
		}
		
		public Optional<Liker> find(Long id) {
			return lR.findById(id);
		}
		
		public ArrayList<Liker> all(){
			return (ArrayList<Liker>) lR.findAll();
		}
		
		public void destroy(Long id) {
			lR.deleteById(id);
		}
		
		public void update(Liker liker) {
			lR.save(liker);
		}
	}

