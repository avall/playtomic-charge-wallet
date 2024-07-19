package com.playtomic.challenge.application.port.output;

import com.playtomic.challenge.domain.model.Wallet;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * OutPort for the use case -> UpdateWalletBalanceUseCase
 */
public interface UpdateWalletBalanceOutPort {

  /**
   * Method signature to create transaction in Database
   * @param id wallet identifier
   * @param amount amount
   * @return Transaction
   */
  Wallet updateWalletBalance(UUID id, BigDecimal amount);
}
