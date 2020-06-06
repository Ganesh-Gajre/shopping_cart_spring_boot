package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ShoppingCartException;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ShoppingCartRepository;
import com.example.demo.response.ShoppingCartResponse;

@Service
@Transactional
public class ShoppingCartReadService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	public ShoppingCartResponse getAllProducts() throws ShoppingCartException {
		ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse();
		try {
			List<Product> products = (List<Product>) productRepository.findAll();
			if (products.size() != 0) {
				shoppingCartResponse.setSuccess();
				shoppingCartResponse.setProducts(products);
			} else {
				throw new ShoppingCartException(404, "No Records Found...!");	
			}
		} catch (Exception e) {
			throw new ShoppingCartException(404, "No Records Found...!", e);
		}
 		return shoppingCartResponse;
	}

	public ShoppingCartResponse getProductById(Integer id) throws ShoppingCartException {
		ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse();
		try {
			Product product = productRepository.findById(id).get();
			if (product.getProductId() != 0) {
				shoppingCartResponse.setSuccess();
				shoppingCartResponse.setProduct(product);
			}
		} catch (Exception e) {
			throw new ShoppingCartException(404, "No Records Found...!", e);
		}
		return shoppingCartResponse;
	}

	public ShoppingCartResponse getProductByName(String name) throws ShoppingCartException {
		ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse();
		try {
			Product product = productRepository.findByProductName(name);
			if (product.getProductId() != 0) {
				shoppingCartResponse.setSuccess();
				shoppingCartResponse.setProduct(product);
			}
		} catch (Exception e) {
			throw new ShoppingCartException(404, "No Records Found...!");
		}
		return shoppingCartResponse;
	}

	public ShoppingCartResponse getShoppingCart() throws ShoppingCartException {
		ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse();
		try {
			Cart cart = shoppingCartRepository.findById(1).get();
			shoppingCartResponse.setSuccess();
			shoppingCartResponse.setCart(cart);
		} catch (Exception e) {
			throw new ShoppingCartException(404, "Shopping cart is empty...!", e);
		}
		return shoppingCartResponse;
	}

}
