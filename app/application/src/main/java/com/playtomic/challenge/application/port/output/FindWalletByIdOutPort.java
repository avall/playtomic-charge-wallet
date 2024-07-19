package com.playtomic.challenge.application.port.output;

import com.playtomic.challenge.domain.model.Wallet;
import java.util.UUID;

/**
 * OutPort for the use case -> FindWalletByIdUseCase
 */
public interface FindWalletByIdOutPort {

  /**
   * Method signature to retrieve wallet from Database
   * @param id wallet identifier
   * @return wallet
   */
  Wallet findWalletById(UUID id);
}
