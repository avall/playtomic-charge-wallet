package com.playtomic.challenge.application.port.input;

import com.playtomic.challenge.application.port.UseCase;
import com.playtomic.challenge.domain.model.Account;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * Interface definition for the se case
 */
public interface FindAllAccountsUseCase
    extends UseCase<FindAllAccountsUseCase.InputValues,
    FindAllAccountsUseCase.OutputValues> {

  /**
   * Output parameters returned from the Use Case
   */
  @Data
  @Builder
  class OutputValues implements UseCase.OutputValues {
    private List<Account> accounts;
  }
}
