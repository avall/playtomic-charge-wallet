package com.playtomic.challenge.application.port.input.interactor;

import com.playtomic.challenge.application.port.input.FindWalletByIdUseCase;
import com.playtomic.challenge.application.port.output.FindWalletByIdOutPort;
import com.playtomic.challenge.domain.decorator.Interactor;
import lombok.RequiredArgsConstructor;

/**
 * Implementation for the Use Case
 */
@Interactor
@RequiredArgsConstructor
public class FindWalletByIdInteractor
    implements FindWalletByIdUseCase {

  private final FindWalletByIdOutPort findWalletByIdOutPort;

  /**
   * Method to execute the Use Case about find wallet by id
   * @param input parameters
   * @return Wallet
   */
  @Override
  public OutputValues execute(
      InputValues input) {
    return OutputValues.builder().wallet(findWalletByIdOutPort.findWalletById(input.getId()))
        .build();
  }
}
