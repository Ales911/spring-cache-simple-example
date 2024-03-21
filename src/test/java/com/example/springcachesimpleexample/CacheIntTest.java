package com.example.springcachesimpleexample;

import java.util.List;
import java.util.stream.StreamSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
public class CacheIntTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    public void setUp() {
        cacheManager.getCache(CachingConfig.CACHE_BOOKS).clear();
    }

    @Test
    public void findAllTest() {
        // When
        final List<Book> actual = StreamSupport
                .stream(bookRepository.findAll().spliterator(), false).toList();

        // Then
        Assertions.assertEquals(3, actual.size());
    }

    @Test
    public void findByIdCacheTest() {
        // Given
        final String isbn1 = "isbn1";
        Assertions.assertFalse(isInCache(isbn1));

        // When
        Assertions.assertNotNull(bookRepository.findById(isbn1));

        // Then
        Assertions.assertTrue(isInCache(isbn1));
    }

    @Test
    @Sql(statements = "DELETE FROM Book WHERE isbn = 'isbn4'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void addCacheTest() {
        // Given
        final String isbn4 = "isbn4";
        Assertions.assertFalse(isInCache(isbn4));

        // When
        Assertions.assertNotNull(bookRepository.save(new Book(isbn4, "author4", "title4")));

        // Then
        Assertions.assertTrue(isInCache(isbn4));
    }

    @Test
    public void updateCacheTest() {
        // Given
        final String isbn3 = "isbn3";
        final Book expected = new Book(isbn3, "author33", "title33");
        Assertions.assertFalse(isInCache(isbn3));
        Assertions.assertNotNull(bookRepository.findById(isbn3));
        Assertions.assertTrue(isInCache(isbn3));

        // When
        final Book actual = bookRepository.save(expected);

        // Then
        Assertions.assertEquals(expected, actual);
        Assertions.assertTrue(isInCache(isbn3));
    }

    @Test
    public void deleteByIdCacheTest() {
        // Given
        final String isbn2 = "isbn2";
        Assertions.assertFalse(isInCache(isbn2));
        Assertions.assertNotNull(bookRepository.findById(isbn2));
        Assertions.assertTrue(isInCache(isbn2));

        // When
        bookRepository.deleteById(isbn2);

        // Then
        Assertions.assertFalse(isInCache(isbn2));
    }

    private boolean isInCache(final String key) {
        final Cache cache = cacheManager.getCache(CachingConfig.CACHE_BOOKS);
        return (cache != null) && (cache.get(key) != null);
    }

}
