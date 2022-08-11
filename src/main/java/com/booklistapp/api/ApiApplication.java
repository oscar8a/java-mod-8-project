package com.booklistapp.api;

import com.booklistapp.api.models.Author;
import com.booklistapp.api.models.Book;
import com.booklistapp.api.models.User;
import com.booklistapp.api.repository.AuthorRepository;
import com.booklistapp.api.repository.BookRepository;
import com.booklistapp.api.repository.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
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

		@Autowired
		AuthorRepository authorRepository;

		Faker faker = new Faker();

		@Override
		public void run(String... args) throws Exception {
			loadUserData();
			loadBookData();
			loadAuthorData();
		}

		private void loadUserData() {
			IntStream.range(0, 10).forEach((i) -> {
				User newUser = new User();
				newUser.setUsername(faker.name().username());
				newUser.setPassword(faker.pokemon().name());
				userRepository.save(newUser);
			});
		}

		private void loadBookData() {
			IntStream.range(0, 10).forEach((i) -> {
				Book newBook = new Book();
				com.github.javafaker.Book fakeBook = faker.book();
				newBook.setTitle(fakeBook.title());
				newBook.setPages(faker.number().numberBetween(1, 1000));
				newBook.setPublished(faker.date().between(new Date(1990, Calendar.JANUARY, 1), new Date(2020, Calendar.MAY, 15)));
				bookRepository.save(newBook);
			});
		}

		private void loadAuthorData() {
			IntStream.rangeClosed(1, 10).forEach((i) -> {
				Author newAuthor = new Author();
				newAuthor.setName(faker.name().fullName());
//				newAuthor.addBook(bookRepository.findById(i).orElse(null));
				authorRepository.save(newAuthor);
			});
		}

		private void loadGenreData() {

		}
	}
}
