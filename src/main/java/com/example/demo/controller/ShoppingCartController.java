package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ShoppingCartException;
import com.example.demo.model.Apparal;
import com.example.demo.model.Book;
import com.example.demo.response.ShoppingCartResponse;
import com.example.demo.service.ShoppingCartReadService;
import com.example.demo.service.ShoppingCartService;

@RestController
@RequestMapping("/api/v1")
public class ShoppingCartController {

	@Autowired
	public ShoppingCartService shoppingCartService;

	@Autowired
	public ShoppingCartReadService shoppingCartReadService;

	@GetMapping("/home")
	public String message() {
		return "Welcome";
	}

	@GetMapping(value = "/getAllProducts")
	public ResponseEntity<ShoppingCartResponse> getAllProducts() {
		ShoppingCartResponse response = new ShoppingCartResponse();
		try {
			response = shoppingCartReadService.getAllProducts();
		} catch (ShoppingCartException e) {
			response.setError();
			response.setHttpStatusCode(e.getHttpStatusCode());
			response.setMessage(e.getMessage());
		}
		return ResponseEntity.status(response.getHttpStatusCode()).body(response);
	}

	@GetMapping(value = "/getProductById/{id}")
	public ResponseEntity<ShoppingCartResponse> getProductById(@PathVariable Integer id) {
		ShoppingCartResponse response = new ShoppingCartResponse();
		try {
			response = shoppingCartReadService.getProductById(id);
		} catch (ShoppingCartException e) {
			response.setError();
			response.setHttpStatusCode(e.getHttpStatusCode());
			response.setMessage(e.getMessage());
		}
		return ResponseEntity.status(response.getHttpStatusCode()).body(response);
	}

	@GetMapping(value = "/getProductByName/{name}")
	public ResponseEntity<ShoppingCartResponse> getProductByName(@PathVariable String name) {
		ShoppingCartResponse response = new ShoppingCartResponse();
		try {
			response = shoppingCartReadService.getProductByName(name);
		} catch (ShoppingCartException e) {
			response.setError();
			response.setHttpStatusCode(e.getHttpStatusCode());
			response.setMessage(e.getMessage());
		}
		return ResponseEntity.status(response.getHttpStatusCode()).body(response);
	}

	@PostMapping(value = "/addBook")
	public ResponseEntity<ShoppingCartResponse> addBook(@RequestBody Book book) {
		ShoppingCartResponse response = new ShoppingCartResponse();
		try {
			response = shoppingCartService.addBook(book);
		} catch (ShoppingCartException e) {
			response.setError();
			response.setHttpStatusCode(e.getHttpStatusCode());
			response.setMessage(e.getMessage());
		}
		return ResponseEntity.status(response.getHttpStatusCode()).body(response);
	}

	@PostMapping(value = "/addApparal")
	public ResponseEntity<ShoppingCartResponse> addApparal(@RequestBody Apparal apparal) {
		ShoppingCartResponse response = new ShoppingCartResponse();
		try {
			response = shoppingCartService.addApparal(apparal);
		} catch (ShoppingCartException e) {
			response.setError();
			response.setHttpStatusCode(e.getHttpStatusCode());
			response.setMessage(e.getMessage());
		}
		return ResponseEntity.status(response.getHttpStatusCode()).body(response);
	}

	@GetMapping(value = "/getShoppingCart")
	public ResponseEntity<ShoppingCartResponse> getShoppingCart() {
		ShoppingCartResponse response = new ShoppingCartResponse();
		try {
			response = shoppingCartReadService.getShoppingCart();
		} catch (ShoppingCartException e) {
			response.setError();
			response.setHttpStatusCode(e.getHttpStatusCode());
			response.setMessage(e.getMessage());
		}
		return ResponseEntity.status(response.getHttpStatusCode()).body(response);
	}

	@PostMapping(value = "/addProductToCart/{id}")
	public ResponseEntity<ShoppingCartResponse> addProductToCart(@PathVariable Integer id) {
		ShoppingCartResponse response = new ShoppingCartResponse();
		try {
			response = shoppingCartService.addProductToCart(id);
		} catch (ShoppingCartException e) {
			response.setError();
			response.setHttpStatusCode(e.getHttpStatusCode());
			response.setMessage(e.getMessage());
		}
		return ResponseEntity.status(response.getHttpStatusCode()).body(response);
	}

	@PutMapping(value = "/updateCartQuantity/{quantity}")
	public ResponseEntity<ShoppingCartResponse> updateCartQuantity(@RequestBody Integer productId,
			@PathVariable Integer quantity) {
		ShoppingCartResponse response = new ShoppingCartResponse();
		try {
			response = shoppingCartService.updateCartQuantity(quantity, productId);
		} catch (ShoppingCartException e) {
			response.setError();
			response.setHttpStatusCode(e.getHttpStatusCode());
			response.setMessage(e.getMessage());
		}
		return ResponseEntity.status(response.getHttpStatusCode()).body(response);
	}

	@DeleteMapping(value = "/removeAllProductsFromCart")
	public ResponseEntity<ShoppingCartResponse> deleteAllProducts() {
		ShoppingCartResponse response = new ShoppingCartResponse();
		try {
			response = shoppingCartService.removeAllProductsFromCart();
		} catch (ShoppingCartException e) {
			response.setError();
			response.setHttpStatusCode(e.getHttpStatusCode());
			response.setMessage(e.getMessage());
		}
		return ResponseEntity.status(response.getHttpStatusCode()).body(response);
	}

	@DeleteMapping(value = "/removeProductFromCart/{productId}")
	public ResponseEntity<ShoppingCartResponse> deleteProductById(@PathVariable Integer productId) {
		ShoppingCartResponse response = new ShoppingCartResponse();
		try {
			response = shoppingCartService.removeProductFromCart(productId);
		} catch (ShoppingCartException e) {
			response.setError();
			response.setHttpStatusCode(e.getHttpStatusCode());
			response.setMessage(e.getMessage());
		}
		return ResponseEntity.status(response.getHttpStatusCode()).body(response);
	}

}
