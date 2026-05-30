package io.github.wkktoria.wlepka.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class PublicPathConfig {

    @Bean
    List<String> publicPaths() {
        return List.of("/api/v1/products/**", "/api/v1/contacts/**");
    }

}
