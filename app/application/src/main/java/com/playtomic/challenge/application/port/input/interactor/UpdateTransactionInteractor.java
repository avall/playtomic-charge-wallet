package com.playtomic.challenge.application.port.input.interactor;

import com.playtomic.challenge.application.port.input.UpdateTransactionUseCase;
import com.playtomic.challenge.application.port.output.UpdateTransactionOutPort;
import com.playtomic.challenge.domain.decorator.Interactor;
import lombok.RequiredArgsConstructor;

/**
 * Implementation for the Use Case
 */
@Interactor
@RequiredArgsConstructor
public class UpdateTransactionInteractor
    implements UpdateTransactionUseCase {

  private final UpdateTransactionOutPort updateTransactionOutPort;

  /**
   * Method to execute the Use Case to create Account
   * @param input parameters
   * @return Account
   */
  @Override
  public OutputValues execute(InputValues input) {
    return OutputValues.builder()
        .transaction(updateTransactionOutPort.updateTransaction(input.getTransaction()))
        .build();
  }
}
