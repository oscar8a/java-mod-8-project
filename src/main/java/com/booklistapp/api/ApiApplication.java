package com.booklistapp.api;

import com.booklistapp.api.models.*;
import com.booklistapp.api.repository.*;
import com.github.javafaker.Faker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
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

		@Autowired
		ReadingListRepository readingListRepository;

		private final Faker faker = new Faker();
		private final Random rand = new Random();

		@Override
		public void run(String... args) throws Exception {
			loadUserData();
			loadGenreData();
			loadBookData();
			loadAuthorData();

			associateBookAuthor();
			associateBookGenre();

			createReadingLists();
		}

		private void loadUserData() {
			IntStream.rangeClosed(1, 10).forEach((i) -> {
				User newUser = new User();
				newUser.setUsername(faker.name().username());
				newUser.setPassword(faker.pokemon().name());
				userRepository.save(newUser);
			});
		}

		private void loadBookData() {
			IntStream.rangeClosed(1, 10).forEach((i) -> {
				Date date = faker.date().past(36525, TimeUnit.DAYS);
				Book newBook = new Book();
				com.github.javafaker.Book fakeBook = faker.book();
				newBook.setTitle(fakeBook.title());
				newBook.setPages(faker.number().numberBetween(1, 1000));
				newBook.setPublished(date);
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

			IntStream.rangeClosed(1, 10).forEach((i) -> {
				Genre genre = genreRepository.findById(rand.nextInt(genreListSize) + 1).orElseThrow();
				Book book = bookRepository.findById(i).orElseThrow();
				genre.getBookList().add(book);
				genreRepository.save(genre);
			});
		}

		private void associateBookAuthor() {
			int authorListSize = authorRepository.findAll().size();

			IntStream.rangeClosed(1, 10).forEach((i) -> {
				Author author = authorRepository.findById(rand.nextInt(authorListSize) + 1).orElseThrow();
				Book book = bookRepository.findById(i).orElseThrow();
				book.setAuthor(author);
				bookRepository.save(book);
			});
		}

		private void createReadingLists() {
			List<User> userList = userRepository.findAll();
			List<Book> bookList = bookRepository.findAll();

			IntStream.rangeClosed(1, 3).forEach((i) -> {
				ReadingList newList = new ReadingList();
				newList.setName(faker.cat().name());
				newList.getBookSet().add(bookList.get(rand.nextInt((bookList.size()))));
				readingListRepository.save(newList);

				User user = userList.get(rand.nextInt((bookList.size())));
				user.getReadingList().add(newList);
				userRepository.save(user);
			});
		}
	}
}
