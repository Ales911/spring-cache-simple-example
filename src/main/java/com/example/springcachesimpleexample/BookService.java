package com.example.springcachesimpleexample;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        logger.info("findAll(): ");
        return bookRepository.findAll();
    }

    @Cacheable(cacheNames = "books", key = "#book.isbn")
    public Book add(Book book) {
        logger.info("add a book with isbn: " + book.getIsbn());
        return bookRepository.add(book);
    }

    @Cacheable(cacheNames = "books")
    public Book findByIsbn(String isbn) {
        logger.info("finding book by isbn: " + isbn);
        return bookRepository.findByIsbn(isbn);
    }

    @CachePut(cacheNames = "books", key = "#book.isbn")
    public Book update(Book book) {
        logger.info("update book by isbn: " + book.getIsbn());
        return bookRepository.updateByIsbn(book.getIsbn(), book);
    }

    @CacheEvict("books")
    public boolean remove(String isbn) {
        logger.info("remove book by isbn: " + isbn);
        return bookRepository.remove(isbn);
    }
    
//    @Caching(
//            cacheable = {
//                    @Cacheable("person"),
//                    @Cacheable("contacts")
//            },
//            put = {
//                    @CachePut("tables"),
//                    @CachePut("chairs"),
//                    @CachePut(value = "emals", key = "#person.email")
//            },
//            evict = {
//                    @CacheEvict(value = "services", key = "#person.name")
//            }
//    )
//    public Person complicatedUpdate() {
//        return null;
//    }

}
