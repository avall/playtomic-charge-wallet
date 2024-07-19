package com.playtomic.challenge.application.port.output;

/**
 * OutPort for the use case -> RefundPaymentUseCase
 */
public interface RefundPaymentOutPort {

  /**
   * Method signature to charge credit card.
   * @param id payment identifier
   */
  void refund(String id);
}
