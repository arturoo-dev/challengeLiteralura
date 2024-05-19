package com.arturodev.challengeLiteralura;

import com.arturodev.challengeLiteralura.main.Main;
import com.arturodev.challengeLiteralura.repository.AuthorsRepository;
import com.arturodev.challengeLiteralura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner {
	@Autowired
	private BookRepository booksRepository;

	@Autowired
	private AuthorsRepository authorsRepository;

	public static void main(String[] args) {

		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(booksRepository, authorsRepository);
		main.showMenu();
	}
}
