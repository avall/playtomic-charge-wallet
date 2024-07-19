package com.playtomic.challenge.application.port.input;

import com.playtomic.challenge.application.port.UseCase;
import com.playtomic.challenge.domain.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Interface definition for the se case
 */
public interface UpdateTransactionUseCase
    extends UseCase<UpdateTransactionUseCase.InputValues,
    UpdateTransactionUseCase.OutputValues> {

  /**
   * Input parameters as input to the Use Case
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  class InputValues implements UseCase.InputValues {
    private Transaction transaction;
  }

  /**
   * Output parameters returned from the Use Case
   */
  @Data
  @Builder
  class OutputValues implements UseCase.OutputValues {
    private Transaction transaction;
  }
}
