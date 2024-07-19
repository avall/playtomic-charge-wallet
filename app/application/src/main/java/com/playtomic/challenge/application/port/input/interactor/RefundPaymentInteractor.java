package com.playtomic.challenge.application.port.input.interactor;

import com.playtomic.challenge.application.port.input.RefundPaymentUseCase;
import com.playtomic.challenge.application.port.output.RefundPaymentOutPort;
import com.playtomic.challenge.domain.decorator.Interactor;
import lombok.RequiredArgsConstructor;

/**
 * Implementation for the Use Case
 */
@Interactor
@RequiredArgsConstructor
public class RefundPaymentInteractor
    implements RefundPaymentUseCase {

  private final RefundPaymentOutPort refundPaymentOutPort;

  /**
   * Method to execute the Use Case about refund payment
   * @param input parameters
   * @return  Empty output
   */
  @Override
  public OutputValues execute(InputValues input) {
    refundPaymentOutPort.refund(input.getPaymentId());
    return OutputValues.builder().build();
  }
}
