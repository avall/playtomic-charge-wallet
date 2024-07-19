package com.playtomic.challenge.main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(
    classes = {WalletApplication.class},
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    properties = {})
class WalletApplicationTest {
  @Autowired ApplicationContext applicationContext;

  @Test
  void testMain() {
    // Setup
    // Run the test
    Assertions.assertAll(
        // Use Cases
        () -> Assertions.assertNotNull(applicationContext.getBean("createChargeTransactionInteractor")),

        // Adapters
        () -> Assertions.assertNotNull(applicationContext.getBean("transactionAdapter")),

        // Repositories
        () -> Assertions.assertNotNull(applicationContext.getBean("transactionRepository"))
    );
  }
}
