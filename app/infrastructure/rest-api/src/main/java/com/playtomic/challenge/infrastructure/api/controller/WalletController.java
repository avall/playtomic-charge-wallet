package com.playtomic.challenge.infrastructure.api.controller;

import com.playtomic.challenge.api.lib.controller.WalletV1Api;
import com.playtomic.challenge.api.lib.model.WalletResponseDto;
import com.playtomic.challenge.application.port.input.FindWalletByIdUseCase;
import com.playtomic.challenge.infrastructure.api.mapper.WalletDtoMapper;
import com.playtomic.challenge.infrastructure.api.presenter.UseCaseExecutorPort;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller from OpenApi definition
 */
@RestController
@RequiredArgsConstructor
public class WalletController implements WalletV1Api, UseCaseExecutorPort {
  private final FindWalletByIdUseCase useCase;
  private final WalletDtoMapper mapper;

  @Override
  public ResponseEntity<WalletResponseDto> getWalletById(UUID walletId) {
    return functional(
        FindWalletByIdUseCase.InputValues
            .builder()
            .id(walletId)
            .build(),
        useCase,
        output -> ResponseEntity.ok().body(mapper.toDto(output.getWallet()))
    );
  }
}
