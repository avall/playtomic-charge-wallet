package com.playtomic.challenge.application.port.input.interactor;

import com.playtomic.challenge.application.port.input.UpdateWalletBalanceUseCase;
import com.playtomic.challenge.application.port.output.UpdateWalletBalanceOutPort;
import com.playtomic.challenge.domain.decorator.Interactor;
import lombok.RequiredArgsConstructor;

/**
 * Implementation for the Use Case
 */
@Interactor
@RequiredArgsConstructor
public class UpdateWalletBalanceInteractor
    implements UpdateWalletBalanceUseCase {

  private final UpdateWalletBalanceOutPort updateWalletBalanceOutPort;

  /**
   * Method to execute the Use Case to create Account
   * @param input parameters
   * @return Account
   */
  @Override
  public OutputValues execute(InputValues input) {
    return OutputValues.builder().wallet(
        updateWalletBalanceOutPort.updateWalletBalance(input.getId(), input.getAmount())).build();
  }
}
