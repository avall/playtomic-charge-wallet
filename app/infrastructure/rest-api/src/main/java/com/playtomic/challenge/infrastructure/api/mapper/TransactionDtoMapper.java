package com.playtomic.challenge.infrastructure.api.mapper;

import com.playtomic.challenge.api.lib.model.TransactionCreateRequestDto;
import com.playtomic.challenge.api.lib.model.TransactionResponseDto;
import com.playtomic.challenge.domain.model.Transaction;
import com.playtomic.challenge.domain.model.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        imports = {Wallet.class}
)
public interface TransactionDtoMapper {
  @Mapping(target = "walletId", expression = "java(entity.getWallet().id())")
  TransactionResponseDto toDto(Transaction entity);

  @Mapping(target = "wallet", expression = "java(Wallet.builder().id(entity.getWalletId()).build())")
  @Mapping(target = "status", expression = "java(TransactionStatus.PENDING)")
  Transaction toDomain(TransactionCreateRequestDto entity);
}
