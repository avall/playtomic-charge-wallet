package com.playtomic.challenge.application.port.output;

import com.playtomic.challenge.domain.model.Account;
import java.util.List;

/**
 * OutPort for the use case -> FindAllAccountsUseCase
 */
public interface FindAllAccountsOutPort {

  /**
   * Method signature to retrieve all accounts from Database
   * @return List of accounts
   */
  List<Account> findAll();
}
