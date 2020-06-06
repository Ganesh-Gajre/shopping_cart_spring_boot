package com.example.demo.response;
import java.util.List;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ShoppingCartResponse {
	
	@JsonIgnore
	private String error = "ERROR", success = "SUCCESS";
	private String status;
	private String message;
	private Product product;
	private List<Product> products;
	private Cart cart;
	private int httpStatusCode = 200;
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public String getError() {
		return error;
	}
	
	@JsonIgnore
	public void setError() {
		this.status = error;
	}
	
	public String getSuccess() {
		return success;
	}
	
	@JsonIgnore
	public void setSuccess() {
		this.status = success;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getHttpStatusCode() {
		return httpStatusCode;
	}
	
	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
	
}
