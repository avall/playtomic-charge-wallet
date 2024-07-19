package com.playtomic.challenge.infrastructure.api.controller;

import com.playtomic.challenge.api.lib.controller.AccountV1Api;
import com.playtomic.challenge.api.lib.model.AccountCreateRequestDto;
import com.playtomic.challenge.api.lib.model.AccountListResponseDto;
import com.playtomic.challenge.api.lib.model.AccountResponseDto;
import com.playtomic.challenge.application.port.input.CreateAccountUseCase;
import com.playtomic.challenge.application.port.input.FindAllAccountsUseCase;
import com.playtomic.challenge.infrastructure.api.mapper.AccountDtoMapper;
import com.playtomic.challenge.infrastructure.api.presenter.UseCaseExecutorPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller from OpenApi definition
 */
@RestController
@RequiredArgsConstructor
public class AccountController implements AccountV1Api, UseCaseExecutorPort {
  private final CreateAccountUseCase createAccountUseCase;
  private final FindAllAccountsUseCase findAllAccountsUseCase;
  private final AccountDtoMapper mapper;

  @Override
  public ResponseEntity<AccountResponseDto> createAccount(
      AccountCreateRequestDto accountCreateRequestDto) {
      return functional(
          CreateAccountUseCase.InputValues
            .builder().account(mapper.toDomain(accountCreateRequestDto)).build(),
          createAccountUseCase,
        output -> ResponseEntity.status(201).body(mapper.toDto(output.getAccount())));
  }

  @Override
  public ResponseEntity<AccountListResponseDto> getAccounts() {
    return functional(
        null,
        findAllAccountsUseCase,
        output -> ResponseEntity.ok().body(
            AccountListResponseDto.builder().accounts(
                mapper.toDto(output.getAccounts())
            ).build()
        )
    );
  }
}
