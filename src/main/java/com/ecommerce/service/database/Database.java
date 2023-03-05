package com.ecommerce.service.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecommerce.service.models.Book;
import com.ecommerce.service.repositories.BookRepository;
@Configuration
public class Database {
	// logger
	private static final Logger logger = LoggerFactory.getLogger(Database.class);

	@Bean
	CommandLineRunner initDatabase(BookRepository bookRepository) {

		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				// TODO Auto-generated method stub
				Book bookA = new Book("Lập trình C", "Hướng dẫn lập trình c", 2400.0, "");
				Book bookB = new Book("Lập trình Java", "Hướng dẫn lập trình java spring boot", 599.0, "");
				logger.info("insert data:" + bookRepository.save(bookA));
				logger.info("insert data:" + bookRepository.save(bookB));
			}
		};
	}
}
