package com.playtomic.challenge.application.port.input;

import com.playtomic.challenge.application.port.UseCase;
import com.playtomic.challenge.domain.model.Wallet;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Interface definition for the se case
 */
public interface FindWalletByIdUseCase
    extends UseCase<FindWalletByIdUseCase.InputValues,
    FindWalletByIdUseCase.OutputValues> {

  /**
   * Input parameters as input to the Use Case
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  class InputValues implements UseCase.InputValues {
    private UUID id;
  }

  /**
   * Output parameters returned from the Use Case
   */
  @Data
  @Builder
  class OutputValues implements UseCase.OutputValues {
    private Wallet wallet;
  }
}
