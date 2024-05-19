package com.arturodev.challengeLiteralura.main;

import com.arturodev.challengeLiteralura.model.*;
import com.arturodev.challengeLiteralura.repository.AuthorsRepository;
import com.arturodev.challengeLiteralura.repository.BookRepository;
import com.arturodev.challengeLiteralura.service.ConvertData;

import com.arturodev.challengeLiteralura.service.RequestAPI;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private static final RequestAPI requestAPI = new RequestAPI();
    private static final ConvertData convertData = new ConvertData();
    private final Scanner input = new Scanner(System.in);
    private List<DataBooks> dataBooks = new ArrayList<>();
    private List<DataAuthor> dataAuthors = new ArrayList<>();
    private final BookRepository bookRepository;
    private final AuthorsRepository authorsRepository;

    public Main(BookRepository booksRepository, AuthorsRepository authorsRepository) {
        this.bookRepository = booksRepository;
        this.authorsRepository = authorsRepository;
    }


    public void showMenu() {
        int option = -1;
        String menu = ("""
                \n      🚀 Bienvenidos a mi aplicacion! 🚀
                =================================================
                1. Buscar Libros 📖
                2. Listar libros registrados 📚
                3. Listar autores registrados 👨🏻‍🏫
                4. Listar autores vivos en un anio determinado 🤵🏼‍
                5. Listar libros por idioma 🎓
                0. Salir. ⚠️
                =================================================
                Ingrese una opcion correcta...
                """);

        while (option != 0) {
            System.out.println(menu);
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    searchBook();
                    break;
                case 2:
                    System.out.println("opicion 2");
                    break;
                case 3:
                    System.out.println("opicion 3");
                    break;
                case 4:
                    System.out.println("opicion 4");
                    break;
                case 5:
                    System.out.println("opicion 5");
                    break;
                case 0:
                    System.out.println("Saliendo del sistema.....");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida.");
            }
        }
    }

    private void searchBook() {
        System.out.print("Ingrese el nombre del libro que desea buscar: ");
        var bookName = input.nextLine();
        var jsonRequest = requestAPI.requestData(URL_BASE + "?search=" + bookName.replace(" ", "+"));
        var searchResult = convertData.convertData(jsonRequest, JsonResult.class);
        Optional<Book> foundBook = searchResult.result().stream()
                .filter(l -> l.title().toUpperCase().contains(bookName.toUpperCase()))
                .findFirst();
        if (foundBook.isEmpty()) {
            System.out.println("Libro no encontrado");
        } else {
            Optional<Author> foundAuthor = foundBook.get().authors().stream()
                    .map(a -> new Author(a.name(), a.birthYear(), a.deathYear())).findFirst();
            if (foundAuthor.isPresent()) {
                DataAuthor dataAuthor;
                dataAuthor = new DataAuthor(
                        foundAuthor.get().name(),
                        foundAuthor.get().birthYear(),
                        foundAuthor.get().deathYear());
                authorsRepository.save(dataAuthor);
                System.out.println("Autor registrado en la base de datos correctamente.");

                DataBooks dataBooksObject = new DataBooks(
                        foundBook.get().title(),
                        dataAuthor,
                        foundBook.get().languages(),
                        foundBook.get().downloadCount()
                );
                System.out.println(dataBooksObject);
                dataBooks.add(dataBooksObject);
                dataAuthor.setBooks(dataBooks);
                bookRepository.save(dataBooksObject);
                System.out.println("Libro guardado en la base de datos correctamente.");
            }
        }
    }
}



