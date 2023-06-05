package com.example.demoCacheAble;

import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class DemoCacheAbleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoCacheAbleApplication.class, args);
    }

    // maximumSize — максимальный размер значений, которые может содержать кэш.
    // refreshAfterWrite — время после записи значения в кэш, после которого оно автоматически обновится.
    // expireAfterAccess — время жизни значения после последнего обращения к нему.
    // expireAfterWrite — время жизни значения после записи в кэш       
    
    // JCacheCacheManager, EhCacheCacheManager, CaffeineCacheManager
    
//    @Bean("myCacheManager")
//    public CacheManager cacheManager() {
//        return new ConcurrentMapCacheManager() {
//            @Override
//            protected Cache createConcurrentMapCache(String name) {
//                return new ConcurrentMapCache(
//                        name,
//                        CacheBuilder.newBuilder()
//                                .expireAfterWrite(1, TimeUnit.SECONDS)
//                                .build().asMap(),
//                        false);
//            }
//        };
//    }

}
