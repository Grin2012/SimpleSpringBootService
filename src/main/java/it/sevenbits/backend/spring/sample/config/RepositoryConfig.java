package it.sevenbits.backend.spring.sample.config;

import it.sevenbits.backend.spring.sample.core.repository.ItemsRepository;
import it.sevenbits.backend.spring.sample.core.repository.SampleItemsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    @Bean
    public ItemsRepository itemsRepository() {
        return new SampleItemsRepository();
    }
}