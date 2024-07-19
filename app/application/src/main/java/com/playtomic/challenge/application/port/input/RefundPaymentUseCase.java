package com.playtomic.challenge.application.port.input;

import com.playtomic.challenge.application.port.UseCase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface RefundPaymentUseCase extends UseCase<RefundPaymentUseCase.InputValues, RefundPaymentUseCase.OutputValues> {
  /**
   * Input parameters as input to the Use Case
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  class InputValues implements UseCase.InputValues {
    private String paymentId;
  }

  /**
   * Output parameters returned from the Use Case
   */
  @Data
  @Builder
  class OutputValues implements UseCase.OutputValues {
  }
}
