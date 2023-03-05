package com.ecommerce.service.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblBook")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//validate 
	@Column(nullable = false, unique = true,length = 300)
	private String bookName;
	private String description;
	private Double price;
	private String image;
	
	public Book() {}

	public Book(String bookName, String description, Double price, String image) {
		super();
		this.bookName = bookName;
		this.description = description;
		this.price = price;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", description=" + description + ", price=" + price
				+ ", image=" + image + "]";
	}
	
	

}
