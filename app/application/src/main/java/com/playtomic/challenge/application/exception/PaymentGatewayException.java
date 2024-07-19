package com.playtomic.challenge.application.exception;

public class PaymentGatewayException extends CoreException {
  public PaymentGatewayException(String message, String code) {
    super(message, code);
  }
}
