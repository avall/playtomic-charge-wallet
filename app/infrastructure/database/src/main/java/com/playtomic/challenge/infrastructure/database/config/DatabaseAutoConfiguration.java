package com.playtomic.challenge.infrastructure.database.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@ComponentScan("com.playtomic.challenge.infrastructure.database")
@EnableJpaAuditing()
@EntityScan("com.playtomic.challenge.infrastructure.database.model")
@EnableJpaRepositories("com.playtomic.challenge.infrastructure.database.repository")
public class DatabaseAutoConfiguration {

}

