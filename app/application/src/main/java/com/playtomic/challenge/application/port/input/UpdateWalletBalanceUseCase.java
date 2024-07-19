package com.playtomic.challenge.application.port.input;

import com.playtomic.challenge.application.port.UseCase;
import com.playtomic.challenge.domain.model.Wallet;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Interface definition for the se case
 */
public interface UpdateWalletBalanceUseCase
    extends UseCase<UpdateWalletBalanceUseCase.InputValues,
    UpdateWalletBalanceUseCase.OutputValues> {

  /**
   * Input parameters as input to the Use Case
   */
  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  class InputValues implements UseCase.InputValues {
    private UUID id;
    private BigDecimal amount;
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
