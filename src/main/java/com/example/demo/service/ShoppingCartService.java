package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.demo.exception.ShoppingCartException;
import com.example.demo.model.Apparal;
import com.example.demo.model.Book;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ShoppingCartRepository;
import com.example.demo.response.ShoppingCartResponse;

@Service
@Transactional
public class ShoppingCartService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	List<Product> products = new ArrayList<Product>();

	public ShoppingCartResponse addBook(Book book) throws ShoppingCartException {
		ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse();
		Product product = productRepository.findByProductName(book.getProductName());
		if (ObjectUtils.isEmpty(product)) {
			productRepository.save(book);
			shoppingCartResponse.setSuccess();
			shoppingCartResponse.setMessage("Record added successfully");
		} else {
			throw new ShoppingCartException(409, "The record already exists...!");
		}
		return shoppingCartResponse;
	}

	public ShoppingCartResponse addApparal(Apparal apparal) throws ShoppingCartException {
		ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse();
		Product product = productRepository.findByProductName(apparal.getProductName());
		if (ObjectUtils.isEmpty(product)) {
			productRepository.save(apparal);
			shoppingCartResponse.setSuccess();
			shoppingCartResponse.setMessage("Record added successfully");
		} else {
			throw new ShoppingCartException(409, "The record already exists...!");
		}
		return shoppingCartResponse;
	}

	public ShoppingCartResponse addProductToCart(Integer id) throws ShoppingCartException {
		ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse();
		Product product = new Product();
		int quantity;
		double totalPrice;
		try {
			product = productRepository.findById(id).get();
			Cart cart = shoppingCartRepository.findById(1).get();
			Cart cartItem = shoppingCartRepository.findByProducts_ProductId(id);
			if (cart.getCartId() != 0) {
				quantity = cart.getQuantity() + 1;
				totalPrice = cart.getTotalPrice() + product.getPrice();
				cart.setQuantity(quantity);
				cart.setTotalPrice(totalPrice);
				if (ObjectUtils.isEmpty(cartItem)) {
					products.add(product);
					cart.setProducts(products);
				}
				shoppingCartRepository.save(cart);
				shoppingCartResponse.setSuccess();
				shoppingCartResponse.setCart(cart);
				shoppingCartResponse.setMessage("Product added successfully to your cart");
			}
		} catch (Exception e) {
			if (product.getProductId() != 0) {
				Cart shoppingCart = new Cart();
				products.add(product);
				shoppingCart.setQuantity(1);
				shoppingCart.setProducts(products);
				shoppingCart.setTotalPrice(product.getPrice());
				shoppingCartRepository.save(shoppingCart);
				shoppingCartResponse.setSuccess();
				shoppingCartResponse.setCart(shoppingCart);
				shoppingCartResponse.setMessage("Product added successfully to your cart");
			} else {
				throw new ShoppingCartException(404, "No Records Found...!", e);
			}
		}
		return shoppingCartResponse;
	}

	public ShoppingCartResponse updateCartQuantity(Integer quantity, Integer productId) throws ShoppingCartException {
		ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse();
		int productQuantity;
		double totalPrice, productPrice = 0;
		if (quantity > 0) {
			Cart cart = shoppingCartRepository.findById(1).get();
			for (Product item : cart.getProducts()) {
				if (item.getProductId() == productId) {
					productPrice = item.getPrice();
				}
			}
			if (productPrice != 0) {
				productQuantity = cart.getQuantity() + quantity;
				totalPrice = cart.getTotalPrice() + (productPrice * quantity);
				cart.setQuantity(productQuantity);
				cart.setTotalPrice(totalPrice);
				shoppingCartRepository.save(cart);
				shoppingCartResponse.setSuccess();
				shoppingCartResponse.setCart(cart);
				shoppingCartResponse.setMessage("Product quantity updated successfully to your cart");
			} else {
				throw new ShoppingCartException(404, "Product not found...!");
			}
		} else if (quantity == 0) {
			shoppingCartRepository.deleteAll();
			products = null;
			shoppingCartResponse.setSuccess();
			shoppingCartResponse.setMessage("Products successfully removed, your cart is empty");
		} else {
			shoppingCartResponse.setError();
			shoppingCartResponse.setMessage("Please provide valid quantity");
			shoppingCartResponse.setHttpStatusCode(422);
		}

		return shoppingCartResponse;
	}

	public ShoppingCartResponse removeAllProductsFromCart() throws ShoppingCartException {
		ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse();
		try {
			shoppingCartRepository.deleteAll();
			products = null;
			shoppingCartResponse.setSuccess();
			shoppingCartResponse.setMessage("Products successfully removed, your cart is empty");
		} catch (Exception e) {
			shoppingCartResponse.setError();
			shoppingCartResponse.setMessage("Error occured while deleting the products");
			shoppingCartResponse.setHttpStatusCode(405);
		}
		return shoppingCartResponse;
	}

	public ShoppingCartResponse removeProductFromCart(Integer productId) throws ShoppingCartException {
		ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse();

		List<Product> productList = new ArrayList<Product>();
		Cart cart = shoppingCartRepository.findByProducts_ProductId(productId);
		if (!ObjectUtils.isEmpty(cart)) {
			productList = cart.getProducts();
			if (productList.size() == 1) {
				shoppingCartRepository.deleteAll();
				shoppingCartResponse.setSuccess();
				shoppingCartResponse.setMessage("Product successfully removed, your cart is empty");
			} else {
				for (int i = 0; i < productList.size(); i++) {
					if (productList.get(i).getProductId() == productId) {
						cart.setQuantity(cart.getQuantity() - 1);
						cart.setTotalPrice(cart.getTotalPrice() - productList.get(i).getPrice());
						productList.remove(i);
					}
				}
				cart.setProducts(productList);
				shoppingCartRepository.save(cart);
				shoppingCartResponse.setSuccess();
				shoppingCartResponse.setCart(cart);
				shoppingCartResponse.setMessage("Product successfully removed");
			}
		} else {
			throw new ShoppingCartException(404, "Product not found...!");
		}

		return shoppingCartResponse;
	}
}
