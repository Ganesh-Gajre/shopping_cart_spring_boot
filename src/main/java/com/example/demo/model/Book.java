package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Book")
public class Book extends Product {

	@Column(name = "genre")
	private String genre;

	@Column(name = "author")
	private String author;

	@Column(name = "publication")
	private String publication;

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

}
