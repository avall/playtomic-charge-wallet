package com.playtomic.challenge.infrastructure.api.controller;

import com.playtomic.challenge.api.lib.controller.TransactionV1Api;
import com.playtomic.challenge.api.lib.model.TransactionCreateRequestDto;
import com.playtomic.challenge.api.lib.model.TransactionResponseDto;
import com.playtomic.challenge.application.port.input.CreateChargeTransactionUseCase;
import com.playtomic.challenge.infrastructure.api.mapper.TransactionDtoMapper;
import com.playtomic.challenge.infrastructure.api.presenter.UseCaseExecutorPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller from OpenApi definition
 */
@RestController
@RequiredArgsConstructor
public class TransactionController implements TransactionV1Api, UseCaseExecutorPort {
  private final CreateChargeTransactionUseCase useCase;
  private final TransactionDtoMapper mapper;

  @Override
  public ResponseEntity<TransactionResponseDto> createTransaction(
      TransactionCreateRequestDto transactionCreateRequestDto) {
    return functional(
        CreateChargeTransactionUseCase.InputValues
            .builder()
            .transaction(mapper.toDomain(transactionCreateRequestDto))
            .build(),
        useCase,
        output -> ResponseEntity.status(201).body(mapper.toDto(output.getTransaction()))
    );
  }
}
