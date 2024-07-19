package com.playtomic.challenge.application.port.output;

import com.playtomic.challenge.domain.model.Transaction;

/**
 * OutPort for the use case -> UpdateTransactionUseCase
 */
public interface UpdateTransactionOutPort {

  /**
   * Method signature to Update transaction in Database
   * @param transaction transaction
   * @return Transaction
   */
  Transaction updateTransaction(Transaction transaction);
}
