package com.playtomic.challenge.application.port.output;

import com.playtomic.challenge.domain.model.Wallet;
import java.util.UUID;

/**
 * OutPort for the use case -> CreateAccountUseCase
 */
public interface CreateWalletOutPort {

  /**
   * Method signature to create Wallet (from account id) in Database
   * @param id the Wallet id is equal to Account id.
   * @return Wallet
   */
  Wallet createWallet(UUID id);
}
