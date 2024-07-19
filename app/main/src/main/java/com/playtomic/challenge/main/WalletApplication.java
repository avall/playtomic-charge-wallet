package com.playtomic.challenge.main;

import com.playtomic.challenge.domain.decorator.Interactor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * Main spring-boot application taking care to load
 * into the Spring Context any class that implements the Interactor.
 */
@ComponentScan(
    basePackages = {"com.playtomic.challenge.*"},
    includeFilters = @ComponentScan.Filter(
    type = FilterType.ANNOTATION,
    classes = {Interactor.class})
)
@SpringBootApplication
public class WalletApplication {

  public static void main(String[] args) {
    SpringApplication.run(WalletApplication.class, args);
  }
}
