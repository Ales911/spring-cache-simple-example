package com.example.springcachesimpleexample;

import java.util.Optional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

public interface BookRepository extends CrudRepository<Book, String> {
    
    @Override
    @Cacheable(cacheNames = CachingConfig.CACHE_BOOKS)
    public Optional<Book> findById(String id);

    @Override
    @CachePut(cacheNames = CachingConfig.CACHE_BOOKS, key = "#p0.isbn")
    public <S extends Book> S save(S entity);

    @Override
    @CacheEvict(cacheNames = CachingConfig.CACHE_BOOKS)
    public void deleteById(String id);

    @Override
    @RestResource(exported = false)
    public long count();
    
    @Override
    @RestResource(exported = false)
    public void deleteAll();

    @Override
    @RestResource(exported = false)
    public void deleteAllById(Iterable<? extends String> ids);

    @Override
    @RestResource(exported = false)
    public Iterable<Book> findAllById(Iterable<String> ids);

    @Override
    @RestResource(exported = false)
    public void delete(Book entity);

    @Override
    @RestResource(exported = false)
    public void deleteAll(Iterable<? extends Book> entities);

    @Override
    @RestResource(exported = false)
    public boolean existsById(String id);

    @Override
    @RestResource(exported = false)
    public <S extends Book> Iterable<S> saveAll(Iterable<S> entities);
    
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
    
}