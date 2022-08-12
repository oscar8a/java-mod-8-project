package com.booklistapp.api;

import com.booklistapp.api.models.Author;
import com.booklistapp.api.models.Book;
import com.booklistapp.api.models.Genre;
import com.booklistapp.api.models.User;
import com.booklistapp.api.repository.AuthorRepository;
import com.booklistapp.api.repository.BookRepository;
import com.booklistapp.api.repository.GenreRepository;
import com.booklistapp.api.repository.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.IntStream;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Component
	public static class DataLoader implements CommandLineRunner {

		@Autowired
		private UserRepository userRepository;

		@Autowired
		private BookRepository bookRepository;

		@Autowired
		private AuthorRepository authorRepository;

		@Autowired
		private GenreRepository genreRepository;

		private final Faker faker = new Faker();

		@Override
		public void run(String... args) throws Exception {
			loadUserData();
			loadGenreData();
			loadBookData();
			loadAuthorData();
			associateBookGenre();
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
			IntStream.rangeClosed(1, 10).forEach((i) -> {
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
				authorRepository.save(newAuthor);
			});
		}

		private void loadGenreData() {
			List<String> genreList = Arrays.asList("Education", "Professional", "Research");
			genreList.forEach(genre -> {
				genreRepository.save(new Genre(genre));
			});
		}

		private void associateBookGenre() {
			int genreListSize = genreRepository.findAll().size();
			Random rand = new Random();

			IntStream.rangeClosed(1, 10).forEach((i) -> {
				Genre genre = genreRepository.findById(rand.nextInt(genreListSize) + 1).orElseThrow();
				Book book = bookRepository.findById(i).orElseThrow();
				genre.getBookList().add(book);
				genreRepository.save(genre);
			});
		}
	}
}
