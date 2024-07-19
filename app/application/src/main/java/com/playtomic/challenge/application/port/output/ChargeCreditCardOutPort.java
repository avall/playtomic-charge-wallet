package com.playtomic.challenge.application.port.output;

import com.playtomic.challenge.domain.model.Payment;
import java.math.BigDecimal;

/**
 * OutPort for the use case -> ChargeCreditCardUseCase
 */
public interface ChargeCreditCardOutPort {

  /**
   * Method signature to charge credit card.
   * @param creditCardNumber credit card number
   * @param amount amount
   * @return Payment
   */
  Payment charge(String creditCardNumber, BigDecimal amount);
}
