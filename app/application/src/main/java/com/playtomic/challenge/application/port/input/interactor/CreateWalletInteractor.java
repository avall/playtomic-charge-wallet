package com.playtomic.challenge.application.port.input.interactor;

import com.playtomic.challenge.application.port.input.CreateWalletUseCase;
import com.playtomic.challenge.application.port.output.CreateWalletOutPort;
import com.playtomic.challenge.domain.decorator.Interactor;
import lombok.RequiredArgsConstructor;

/**
 * Implementation for the Use Case
 */
@Interactor
@RequiredArgsConstructor
public class CreateWalletInteractor
    implements CreateWalletUseCase {

  private final CreateWalletOutPort createWalletOutPort;

  /**
   * Method to execute the Use Case to create Account
   * @param input parameters
   * @return Account
   */
  @Override
  public OutputValues execute(
      InputValues input) {
    return OutputValues.builder()
        .wallet(createWalletOutPort.createWallet(input.getAccountId())).build();
  }
}
