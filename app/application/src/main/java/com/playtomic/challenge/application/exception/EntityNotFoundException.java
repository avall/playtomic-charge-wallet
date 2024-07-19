package com.playtomic.challenge.application.exception;

public class EntityNotFoundException extends CoreException {
  public EntityNotFoundException(String message, String code) {
    super(message, code);
  }
}
