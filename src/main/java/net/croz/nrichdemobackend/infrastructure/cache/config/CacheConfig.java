package net.croz.nrichdemobackend.infrastructure.cache.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration(proxyBeanMethods = false)
public class CacheConfig {

}
