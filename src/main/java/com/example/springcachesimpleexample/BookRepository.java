package com.example.springcachesimpleexample;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BookRepository {

    private final List<Book> books = new ArrayList<>();
    
    public BookRepository() {
        books.add(new Book("isbn1", "author1", "title1"));
        books.add(new Book("isbn2", "author2", "title2"));
        books.add(new Book("isbn3", "author3", "title3"));
    }
    
    public List<Book> findAll() {
        return books;
    }
    
    public Book add(Book book) {
        books.add(book);
        return book;
    }

    public boolean remove(Book book) {
        return books.remove(book);
    }

    public Book findByIsbn(String isbn) {
        return books.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                // .orElse(null);
                .orElseThrow(() -> new IllegalArgumentException("No book found the isbn: " + isbn));
    }
    
    public Book updateByIsbn(String isbn, Book book) {
        Book result = books.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No book found the isbn: " + isbn));
        result.setAuthor(book.getAuthor());
        result.setTitle(book.getTitle());
        return result;
    }
    
    public boolean remove(String isbn) {
        return books.removeIf(b -> isbn.equals(b.getIsbn()));
    }

}