package com.jonathan.beltexam.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jonathan.beltexam.models.Product;
import com.jonathan.beltexam.repositories.ProductRepository;


@Service
public class ProductService {
	private ProductRepository pR;
	
	public ProductService(ProductRepository pR) {
		this.pR = pR;
	}
	public void create(Product product) {
		pR.save(product);
	}
	
	public Optional<Product> find(Long id) {
		return pR.findById(id);
	}
	
	public ArrayList<Product> all(){
		return (ArrayList<Product>) pR.findAll();
	}
	
	public void destroy(Long id) {
		pR.deleteById(id);
	}
	
	public void update(Product product) {
		pR.save(product);
	}
	public Optional<Product> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
