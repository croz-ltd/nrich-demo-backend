package net.croz.nrichdemobackend.infrastructure.persistence.config;

import net.croz.nrich.search.api.factory.SearchExecutorJpaRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "net.croz.nrichdemobackend", repositoryFactoryBeanClass = SearchExecutorJpaRepositoryFactoryBean.class)
@EnableJpaAuditing
@Configuration(proxyBeanMethods = false)
public class SpringDataJpaConfig {

}
