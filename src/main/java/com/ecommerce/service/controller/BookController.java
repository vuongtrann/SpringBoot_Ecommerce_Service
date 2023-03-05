package com.ecommerce.service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ecommerce.service.models.Book;
import com.ecommerce.service.models.ResponseObject;
import com.ecommerce.service.repositories.BookRepository;

@RestController
@RequestMapping(path = "/api/v1/books")
public class BookController {
	// DI = Dependency Injection
	@Autowired
	private BookRepository repository;

	// GET ALL BOOK
	@GetMapping("")
	// Request is : http://localhost:{server.port}/api/v1/books
	List<Book> getAllbooks() {
		return repository.findAll();
	}

	// GET BOOK BY ID

	@GetMapping("/{id}")
	ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
		Optional<Book> foundBook = repository.findById(id);
		if (foundBook.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Query book successfully", foundBook));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject("false", "Cannot find book with id =" + id, ""));
		}
	}

	@PostMapping("/insert")
	// insert new book with POST method
	ResponseEntity<ResponseObject> insertBook(@RequestBody Book newBook) {
		// checking book name already
		List<Book> foundBook = repository.findByBookName(newBook.getBookName().trim());
		if (foundBook.size() > 0) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("Failed", "Book name already taken ", ""));
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("OK", "Insert book successfully", repository.save(newBook)));

	}

	// update, upsert = update if found, otherwire insert
	@PutMapping("/{id}")
	ResponseEntity<ResponseObject> updateBook(@RequestBody Book newBook, @PathVariable Long id) {
		Book updateBook = repository.findById(id).map(book -> {
			book.setBookName(newBook.getBookName());
			book.setDescription(newBook.getDescription());
			book.setPrice(newBook.getPrice());
			book.setImage(newBook.getImage());
			return repository.save(book);
		}).orElseGet(() -> {
			newBook.setId(id);
			return repository.save(newBook);
		});
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("OK", "Update book successfully", updateBook));
	}
	
	//Delete book
	
	@DeleteMapping("/{id}")
	ResponseEntity<ResponseObject> deleteBook(@PathVariable Long id){
		boolean exists = repository.existsById(id);
		if(exists) {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Delete book successfully", ""));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("Failed", "Cannot find book to delete ", ""));
	}
	

}
