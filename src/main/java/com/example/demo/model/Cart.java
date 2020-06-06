package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart implements Comparable<Cart> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id", nullable = false)
	private int cartId;

	@Column(name = "quantity", nullable = false)
	private int quantity;

	@Column(name = "total_price", nullable = false)
	private double totalPrice;

	@OneToMany
	@JoinTable(name = "cart_product", joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> products = new ArrayList<Product>();

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public int compareTo(Cart cart) {
		if (totalPrice == cart.totalPrice)
			return 0;
		else if (totalPrice > cart.totalPrice)
			return 1;
		else
			return -1;
	}

	@Override
	public int hashCode() {
		return this.cartId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) 
            return true; 
		return false;  
	}
}
