package com.playtomic.challenge.application.port.output;

import com.playtomic.challenge.domain.model.Transaction;

/**
 * OutPort for the use case -> CreateChargeTransactionUseCase
 */
public interface CreateTransactionOutPort {

  /**
   * Method signature to create transaction in Database
   * @param transaction transaction
   * @return Transaction
   */
  Transaction createTransaction(Transaction transaction);
}
