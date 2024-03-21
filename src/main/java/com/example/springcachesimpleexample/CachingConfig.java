package com.example.springcachesimpleexample;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.cache.CacheBuilder;
import java.time.Duration;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableCaching
public class CachingConfig {

    public static final String CACHE_BOOKS = "books";

    @Bean
    @Primary
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager(CACHE_BOOKS);
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .initialCapacity(200)
                .maximumSize(500)
                .expireAfterAccess(Duration.ofSeconds(30))
                .expireAfterWrite(Duration.ofSeconds(30))
                // .weakKeys()
                .recordStats());
        return cacheManager;
    }

    @Bean
    public CacheManager simpleCacheManager() {
        return new ConcurrentMapCacheManager(CACHE_BOOKS) {
            @Override
            protected Cache createConcurrentMapCache(String name) {
                return new ConcurrentMapCache(
                        name,
                        CacheBuilder.newBuilder()
                                .maximumSize(100)
                                // .refreshAfterWrite(30, TimeUnit.SECONDS)
                                // .expireAfterAccess(30, TimeUnit.SECONDS)
                                .expireAfterWrite(30, TimeUnit.SECONDS)
                                .build().asMap(),
                        false);
            }
        };
    }

}