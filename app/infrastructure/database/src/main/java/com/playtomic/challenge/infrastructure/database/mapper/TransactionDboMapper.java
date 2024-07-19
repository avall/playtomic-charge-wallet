package com.playtomic.challenge.infrastructure.database.mapper;

import com.playtomic.challenge.domain.model.Transaction;
import com.playtomic.challenge.infrastructure.database.model.TransactionDbo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {WalletDboMapper.class})
public interface TransactionDboMapper {

  Transaction toDomain(TransactionDbo entity);
  TransactionDbo toDbo(Transaction entity);
}
