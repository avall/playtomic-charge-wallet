package com.playtomic.challenge.infrastructure.database.adapter;

import com.playtomic.challenge.application.error.CommonErrorMessage;
import com.playtomic.challenge.application.exception.EntityNotFoundException;
import com.playtomic.challenge.application.port.output.CreateWalletOutPort;
import com.playtomic.challenge.application.port.output.FindWalletByIdOutPort;
import com.playtomic.challenge.application.port.output.UpdateWalletBalanceOutPort;
import com.playtomic.challenge.domain.model.Wallet;
import com.playtomic.challenge.infrastructure.database.mapper.WalletDboMapper;
import com.playtomic.challenge.infrastructure.database.repository.WalletRepository;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletAdapter implements UpdateWalletBalanceOutPort, FindWalletByIdOutPort,
    CreateWalletOutPort {

  private final WalletRepository repository;
  private final WalletDboMapper mapper;

  @Override
  public Wallet findWalletById(UUID id) {
    return repository.findById(id)
        .map(mapper::toDomain)
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("Wallet with id %s not found", id),
            CommonErrorMessage.RESOURCE_NOT_FOUND.getCode()));
  }

  @Override
  public Wallet updateWalletBalance(UUID id, BigDecimal amount) {
    repository.updateBalance(id, amount);
    return findWalletById(id);
  }

  @Override
  public Wallet createWallet(UUID id) {
    return mapper.toDomain(repository.save(
        mapper.toDbo(
            Wallet.builder().id(id).balance(BigDecimal.ZERO).build()
        ))
    );
  }
}
