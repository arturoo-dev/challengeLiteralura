package com.arturodev.challengeLiteralura.main;

import com.arturodev.challengeLiteralura.model.*;
import com.arturodev.challengeLiteralura.repository.AuthorsRepository;
import com.arturodev.challengeLiteralura.repository.BookRepository;
import com.arturodev.challengeLiteralura.service.ConvertData;

import com.arturodev.challengeLiteralura.service.RequestAPI;


import java.util.*;

public class Main {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private static final RequestAPI requestAPI = new RequestAPI();
    private static final ConvertData convertData = new ConvertData();
    private final Scanner input = new Scanner(System.in);
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
            System.out.print(menu);
            try {
                option = input.nextInt();
                input.nextLine(); // Limpiamos el buffer
                switch (option) {
                    case 1:
                        searchBook();
                        break;
                    case 2:
                        showBooks();
                        break;
                    case 3:
                        showAuthors();
                        break;
                    case 4:
                        showAuthorsByYear();
                        break;
                    case 5:
                        showBookByLanguage();
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema.....");
                        break;
                    default:
                        System.out.println(" ❌ Ingrese una opción válida. ❌");
                }
            } catch (Exception e) {
                System.out.println("Error: debes ingresar un numero!");
                input.nextLine(); // Limpiamos el buffer en caso de error
            }
        }

        input.close(); // Cerramos el scanner al salir del bucle
    }

    private void searchBook() {
        System.out.print("Ingrese el nombre del libro que desea buscar: ");
        var bookName = input.nextLine();
        var jsonRequest = requestAPI.requestData(URL_BASE + "?search=" + bookName.replace(" ", "+"));
        var searchResult = convertData.convertData(jsonRequest, JsonResult.class);

        //Libro obtenido de la base de datos
        Optional<DataBooks> foundBook = searchResult.result().stream()
                .map(DataBooks::new)
                .findFirst();

        if (foundBook.isPresent()) {
            //Buscamos el autor en la base de datos
            DataAuthor author  = authorsRepository.findByNameContainsIgnoreCase(foundBook.get().getAuthor().getName())
                    .orElseGet(() -> {
                        DataAuthor newAuthor = foundBook.get().getAuthor();
                        return authorsRepository.save(newAuthor);
                    });
            DataBooks book = foundBook.get();
            //Buscamos el libro en la base de datos y comparamos en el filter si coincide con el que viene de la api.
            DataBooks bookDatabase = bookRepository.findAll().stream()
                    .filter(b -> b.getTitle().equals(book.getTitle()))
                    .findFirst()
                    .orElse(null);

            if (bookDatabase == null) {
                book.setAuthor(author);
                bookRepository.save(book);
                System.out.println("\nLibro registrado exitosamente. ✅✅ ");
            } else {
                System.out.println("Este libro ya se encuentra registrado en la base de datos ⚠️⚠️");
            }
        } else {
            System.out.println("Este libro no encontrado en la api, intente nuevamente. ⚠️⚠️");
        }
    }

    private void showBooks() {
        List<DataBooks> booksDataBase = bookRepository.findAll();
        booksDataBase.forEach(System.out::println);
    }

    private void showAuthors() {
        List<DataAuthor> authorsDataBase = authorsRepository.findAll();
        authorsDataBase.forEach(System.out::println);
    }

    private void showAuthorsByYear() {
        System.out.print("Ingrese el anio que desea buscar un autor vivo: ");
        try {
            int year = input.nextInt();
            List<DataAuthor> authorsDataBase = authorsRepository.findByDeathYear(year);
            authorsDataBase.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: debes ingresar un numero!");
            input.nextLine(); // Limpiamos el buffer en caso de error
        }
    }

    private void showBookByLanguage() {
        System.out.print("""
                        |----------------------|
                        |  Idiomas disponibles:|
                        |                      |
                        |    es -> Espaniol    |
                        |    en -> Ingles      |
                        |    fr -> Frances     |
                        |    pt -> Portugues   |
                        |                      |
                        |----------------------|
                """);

        String language = input.nextLine();
        List<DataBooks> booksDataBase = bookRepository.findAll();
        if (!language.equals("es") && !language.equals("en") && !language.equals("fr") && !language.equals("pt")) {
            System.out.println("Idioma no valido. ❌ --- > Ejemplo: 'es', 'en', 'fr', 'pt'");
            return;
        } else {
            booksDataBase = booksDataBase.stream()
                    .filter(b -> b.getLanguages().contains(language))
                    .toList();
            booksDataBase.forEach(System.out::println);
        }
        input.nextLine(); // Limpiamos el buffer en caso de error
    }

}






