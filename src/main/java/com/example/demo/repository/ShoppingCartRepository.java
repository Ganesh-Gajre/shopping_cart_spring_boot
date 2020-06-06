package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cart;

@Repository
public interface ShoppingCartRepository extends CrudRepository<Cart, Integer> {
	
	Cart findByProducts_ProductId(int id);
	
	Cart deleteByProducts_ProductId(int id);
}
