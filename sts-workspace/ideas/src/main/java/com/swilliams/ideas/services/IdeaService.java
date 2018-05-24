package com.swilliams.ideas.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.swilliams.ideas.models.Idea;
import com.swilliams.ideas.repositories.IdeaRepository;

@Service
public class IdeaService {
	private IdeaRepository iR;
	
	public IdeaService(IdeaRepository iR) {
		this.iR = iR;
	}
	public void create(Idea idea) {
		iR.save(idea);
	}
	
	public Optional<Idea> find(Long id) {
		return iR.findById(id);
	}
	
	public ArrayList<Idea> all(){
		return (ArrayList<Idea>) iR.findAll();
	}
	
	public void destroy(Long id) {
		iR.deleteById(id);
	}
	
	public void update(Idea idea) {
		iR.save(idea);
	}
}
