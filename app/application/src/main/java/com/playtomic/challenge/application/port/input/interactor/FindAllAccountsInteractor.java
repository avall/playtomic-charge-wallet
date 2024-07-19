package com.playtomic.challenge.application.port.input.interactor;

import com.playtomic.challenge.application.port.input.FindAllAccountsUseCase;
import com.playtomic.challenge.application.port.output.FindAllAccountsOutPort;
import com.playtomic.challenge.domain.decorator.Interactor;
import lombok.RequiredArgsConstructor;

/**
 * Implementation for the Use Case
 */
@Interactor
@RequiredArgsConstructor
public class FindAllAccountsInteractor
    implements FindAllAccountsUseCase {

  private final FindAllAccountsOutPort findAllAccountsOutPort;

  /**
   * Method to execute the Use Case about find wallet by id
   * @param input parameters
   * @return Wallet
   */
  @Override
  public OutputValues execute(InputValues input) {
    return OutputValues.builder().accounts(findAllAccountsOutPort.findAll()).build();
  }
}
