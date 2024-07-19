package com.playtomic.challenge.application.port.input.interactor;

import com.playtomic.challenge.application.port.input.ChargeCreditCardUseCase;
import com.playtomic.challenge.application.port.output.ChargeCreditCardOutPort;
import com.playtomic.challenge.domain.decorator.Interactor;
import lombok.RequiredArgsConstructor;

/**
 * Implementation for the Use Case
 */
@Interactor
@RequiredArgsConstructor
public class ChargeCreditCardInteractor
    implements ChargeCreditCardUseCase {

  private final ChargeCreditCardOutPort chargeCreditCardOutPort;

  /**
   * Method to execute the Use Case about charge credit card
   * @param input parameters
   * @return  Payment
   */
  @Override
  public ChargeCreditCardUseCase.OutputValues execute(ChargeCreditCardUseCase.InputValues input) {
    return OutputValues.builder().payment(
            chargeCreditCardOutPort.charge(input.getCreditCardNumber(), input.getAmount()))
        .build();
  }
}
