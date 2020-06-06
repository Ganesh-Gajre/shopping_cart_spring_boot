package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
public class Product implements Comparable<Product> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", nullable = false)
	private int productId;

	@Column(name = "product_name", nullable = false)
	private String productName;

	@Column(name = "price", nullable = false)
	private double price;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int compareTo(Product product) {
		if (price == product.price)
			return 0;
		else if (price > product.price)
			return 1;
		else
			return -1;
	}

	@Override
	public int hashCode() {
		return this.productId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) 
            return true; 
		return false;  
	}
}
