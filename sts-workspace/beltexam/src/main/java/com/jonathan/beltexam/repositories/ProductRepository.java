package com.jonathan.beltexam.repositories;
import org.springframework.data.repository.CrudRepository;

import com.jonathan.beltexam.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
