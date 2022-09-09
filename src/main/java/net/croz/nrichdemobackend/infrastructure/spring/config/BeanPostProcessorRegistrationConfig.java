package net.croz.nrichdemobackend.infrastructure.spring.config;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class BeanPostProcessorRegistrationConfig {

    // the AutowiredAnnotationBeanPostProcessor is explicitly disabled when using spring native (see https://github.com/spring-projects-experimental/spring-native/commit/16b62d5652ff508d8378d2671c875653d32bccd2),
    // but to be able to use validators with dependencies it needs to be present, here I am enabling it again but after application has started
    @Bean
    CommandLineRunner registerAutowiredBeanPostProcessor(AbstractBeanFactory abstractBeanFactory) {
        return args -> {
            if (abstractBeanFactory.getBeanPostProcessors().stream().noneMatch(AutowiredAnnotationBeanPostProcessor.class::isInstance)) {
                abstractBeanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
            }
        };
    }
}
