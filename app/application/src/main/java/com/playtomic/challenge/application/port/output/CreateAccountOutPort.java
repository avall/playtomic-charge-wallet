package com.playtomic.challenge.application.port.output;

import com.playtomic.challenge.domain.model.Account;

/**
 * OutPort for the use case -> CreateAccountUseCase
 */
public interface CreateAccountOutPort {

  /**
   * Method signature to create Account in Database
   * @param account account domain entity
   * @return Account
   */
  Account createAccount(Account account);
}
