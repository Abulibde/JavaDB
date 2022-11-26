package com.example.springintro;

import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        // seedData();

        BigDecimal upperBoundary = new BigDecimal(40);
        BigDecimal lowerBoundary = new BigDecimal(5);

        printBookTitlesAndPricesWithPriceLowerThanAndPriceUpperThan(upperBoundary,lowerBoundary);

    }

    private void printBookTitlesAndPricesWithPriceLowerThanAndPriceUpperThan(BigDecimal upperBoundary, BigDecimal lowerBoundary) {
        this.bookService.findByPriceLessThanOrPriceGreaterThan(lowerBoundary,upperBoundary)
                .forEach(book -> System.out.println(book.getTitle() + " - " + book.getPrice()));
    }

    private void printAllBookTitlesByEditionTypeAndCopiesLessThan(EditionType editionType, int copies) {
        this.bookService.findAllTitlesByCopiesLessThan(EditionType.GOLD, copies)
                .forEach(System.out::println);
    }

    private void printAllBookTitlesByAgeRestriction(String restriction) {
        this.bookService.findAllTitlesByAgeRestriction(restriction)
                .forEach(System.out::println);
    }

    private void printAllBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
