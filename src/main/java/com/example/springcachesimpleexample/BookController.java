package com.example.springcachesimpleexample;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/book")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    private final static Logger log = LoggerFactory.getLogger(BookController.class);
    
    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        log.info("call bookService findAll()");
        List<Book> books = bookService.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(books);
    }
    
    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        log.info("call bookService for create a book with isbn {}", book.getIsbn());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.add(book));
    }
    
    @GetMapping(path = "/{isbn}")
    public ResponseEntity<Book> getBook(@PathVariable String isbn) {
        log.info("call bookService for isbn {}", isbn);
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookService.findByIsbn(isbn));        
    }
    
    @PutMapping()
    public ResponseEntity<Book> update(@RequestBody Book book) {
        log.info("call bookService for update a book with isbn {}", book.getIsbn());
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(bookService.update(book));
    }
    
    @DeleteMapping("/{isbn}")
    public ResponseEntity delete(@PathVariable String isbn) {
        log.info("call bookService for delete a book with isbn {}", isbn);
        bookService.remove(isbn);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}