package com.example.springcachesimpleexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableCaching
public class SpringCacheSimpleExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCacheSimpleExampleApplication.class, args);
    }

//    @Bean
//    public CacheManager cacheManager() {
//        SimpleCacheManager cacheManager = new SimpleCacheManager();
//        cacheManager.setCaches(Arrays.asList(
//                new ConcurrentMapCache("persons"),
//                new ConcurrentMapCache("addresses")));
//        return cacheManager;
//    }

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
