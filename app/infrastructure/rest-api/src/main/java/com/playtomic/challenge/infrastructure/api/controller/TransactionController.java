package com.playtomic.challenge.infrastructure.api.controller;

import com.playtomic.challenge.api.lib.controller.TransactionV1Api;
import com.playtomic.challenge.api.lib.model.TransactionCreateRequestDto;
import com.playtomic.challenge.api.lib.model.TransactionResponseDto;
import com.playtomic.challenge.application.port.input.CreateChargeTransactionUseCase;
import com.playtomic.challenge.application.port.input.CreateRefundTransactionUseCase;
import com.playtomic.challenge.domain.model.TransactionType;
import com.playtomic.challenge.infrastructure.api.mapper.TransactionDtoMapper;
import com.playtomic.challenge.infrastructure.api.presenter.UseCaseExecutorPort;
import java.util.Map;
import java.util.function.Function;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller from OpenApi definition
 */
@RestController
public class TransactionController implements TransactionV1Api, UseCaseExecutorPort {
  private final CreateChargeTransactionUseCase useCaseCharge;
  private final CreateRefundTransactionUseCase useCaseRefund;
  private final TransactionDtoMapper mapper;
  private final Map<TransactionType,
      Function<TransactionCreateRequestDto, ResponseEntity<TransactionResponseDto>>> useCases;


  public TransactionController(
      CreateChargeTransactionUseCase useCaseCharge,
      CreateRefundTransactionUseCase useCaseRefund,
      TransactionDtoMapper mapper) {

    this.mapper = mapper;
    this.useCaseCharge = useCaseCharge;
    this.useCaseRefund = useCaseRefund;
    this.useCases  = Map.of(
        TransactionType.TOPUP, dto -> {
          return functional(
              CreateChargeTransactionUseCase.InputValues
                  .builder()
                  .transaction(mapper.toDomain(dto))
                  .build(),
              useCaseCharge,
              output -> ResponseEntity.status(201).body(mapper.toDto(output.getTransaction())
          ));
        },
        TransactionType.REFUND, dto -> {
          return functional(
              CreateChargeTransactionUseCase.InputValues
                  .builder()
                  .transaction(mapper.toDomain(dto))
                  .build(),
              useCaseCharge,
              output -> ResponseEntity.status(201).body(mapper.toDto(output.getTransaction())
          ));
        }
    );

  }
  @Override
  public ResponseEntity<TransactionResponseDto> createTransaction(
      TransactionCreateRequestDto transactionCreateRequestDto) {
    return useCases.get(TransactionType
        .valueOf(transactionCreateRequestDto.getType().toString()))
        .apply(transactionCreateRequestDto);
  }
}
