package com.ecommerce.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.service.models.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	List<Book> findByBookName(String bookName);
}
