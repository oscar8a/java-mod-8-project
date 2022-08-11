package com.booklistapp.api;

import com.booklistapp.api.models.Book;
import com.booklistapp.api.models.User;
import com.booklistapp.api.repository.BookRepository;
import com.booklistapp.api.repository.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Component
	public class DataLoader implements CommandLineRunner {

		@Autowired
		UserRepository userRepository;

		@Autowired
		BookRepository bookRepository;

		@Override
		public void run(String... args) throws Exception {
			loadUserData();
			loadBookData();
		}

		private void loadUserData() {
			Faker faker = new Faker();
			IntStream.range(0, 10).forEach((i)-> {
				User newUser = new User();
				newUser.setUsername(faker.name().username());
				newUser.setPassword(faker.pokemon().name());
				userRepository.save(newUser);
			});
		}

		private void loadBookData() {
			Faker faker = new Faker();
			IntStream.range(0, 10).forEach((i)-> {
				Book newBook = new Book();
				newBook.setTitle(faker.name().title());
				newBook.setPages(faker.number().numberBetween(1, 1000));
				bookRepository.save(newBook);
			});
		}

		private void loadAuthorData() {

		}

		private void loadGenreData() {

		}
	}
}
