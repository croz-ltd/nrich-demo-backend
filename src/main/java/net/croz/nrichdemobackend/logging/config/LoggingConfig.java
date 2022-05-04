package net.croz.nrichdemobackend.logging.config;

import net.croz.nrich.logging.api.service.LoggingService;
import net.croz.nrich.logging.service.Slf4jLoggingService;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
class LoggingConfig {

    @Bean
    LoggingService loggingService(MessageSource messageSource) {
        return new Slf4jLoggingService(messageSource);
    }
}
