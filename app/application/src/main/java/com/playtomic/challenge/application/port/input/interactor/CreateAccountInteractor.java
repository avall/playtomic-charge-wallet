package com.playtomic.challenge.application.port.input.interactor;

import com.playtomic.challenge.application.port.input.CreateAccountUseCase;
import com.playtomic.challenge.application.port.input.CreateWalletUseCase;
import com.playtomic.challenge.application.port.output.CreateAccountOutPort;
import com.playtomic.challenge.domain.decorator.Interactor;
import lombok.RequiredArgsConstructor;

/**
 * Implementation for the Use Case
 */
@Interactor
@RequiredArgsConstructor
public class CreateAccountInteractor
    implements CreateAccountUseCase {

  private final CreateWalletUseCase createWalletUseCase;
  private final CreateAccountOutPort createAccountOutPort;

  /**
   * Method to execute the Use Case to create Account
   * @param input parameters
   * @return Account
   */
  @Override
  public OutputValues execute(
      InputValues input) {
    var account = createAccountOutPort.createAccount(input.getAccount());
    createWalletUseCase.execute(
        CreateWalletUseCase.InputValues.builder()
            .accountId(account.id()).build()
    );

    return OutputValues.builder()
        .account(account)
        .build();
  }
}
