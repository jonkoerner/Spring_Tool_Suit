package com.jonathan.tv.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jonathan.tv.models.Show;
import com.jonathan.tv.repositories.ShowRepository;

@Service
public class ShowService {
	private ShowRepository sR;
	
	public ShowService(ShowRepository sR) {
		this.sR = sR;
	}
	
	public void create(Show show) {
		sR.save(show);
	}

	public ArrayList<Show> all(){
    	return (ArrayList<Show>)sR.findAll();
    }
//		 
//	public Optional<Show> find(Long id) {
//		return sR.findById(id);
//	}
//	
//	public Iterable<Show> findAllById(Iterable<Long> ids) {
//		return sR.findAllById(ids);
//	}
//
//	public void update(Show show) {
//		sR.save(show);
//	}
//	
//	public void destroy(Long id) {
//		sR.deleteById(id);
//	}
//	
//	public Optional<Show> findById(Long id) {
//		return null;
//	}
}