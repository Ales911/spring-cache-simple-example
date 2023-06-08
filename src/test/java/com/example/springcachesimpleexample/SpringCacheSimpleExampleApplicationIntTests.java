package com.example.springcachesimpleexample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SpringCacheSimpleExampleApplicationIntTests {

    @Autowired
    private BookController bookController;

    @Autowired
    private CacheManager cacheManager;

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        cacheManager.getCache(CachingConfig.CACHE_BOOKS).clear();
    }

    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testFindAll() {
        assertTrue(bookController.findAll().getBody().size() > 0);
    }

    @Test
    public void testFindByIsbn() {
        assertNull(cacheManager.getCache(CachingConfig.CACHE_BOOKS).get("isbn1"));
        assertNotNull(bookController.getBook("isbn1"));
        assertNotNull(cacheManager.getCache(CachingConfig.CACHE_BOOKS).get("isbn1"));
        assertNotNull(bookController.getBook("isbn1"));
    }
    
    @Test
    public void testCreate() {
        assertNull(cacheManager.getCache(CachingConfig.CACHE_BOOKS).get("isbn4"));
        assertNotNull(bookController.create(new Book("isbn4", "author4", "title4")));
        assertNotNull(cacheManager.getCache(CachingConfig.CACHE_BOOKS).get("isbn4"));
    }
    
    @Test
    public void testUpdate() {
        assertNull(cacheManager.getCache(CachingConfig.CACHE_BOOKS).get("isbn2"));
        assertNotNull(bookController.update(new Book("isbn2", "author22", "title22")));
        assertNotNull(cacheManager.getCache(CachingConfig.CACHE_BOOKS).get("isbn2"));
        assertEquals("author22", bookController.getBook("isbn2").getBody().getAuthor());
        assertEquals("title22", bookController.getBook("isbn2").getBody().getTitle());
    }
    
    @Test
    public void testDelete() {
        assertNull(cacheManager.getCache(CachingConfig.CACHE_BOOKS).get("isbn3"));
        assertNotNull(bookController.getBook("isbn3"));
        assertNotNull(cacheManager.getCache(CachingConfig.CACHE_BOOKS).get("isbn3"));
        bookController.delete("isbn3");
        assertNull(cacheManager.getCache(CachingConfig.CACHE_BOOKS).get("isbn3"));
    }
    
}