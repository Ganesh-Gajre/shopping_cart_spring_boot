package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

	/**
	 * This method will find an Product instance in the database by its productName.
	 * 
	 * @param name of the product.
	 * @return Product details.
	 */
	Product findByProductName(String name);

}
