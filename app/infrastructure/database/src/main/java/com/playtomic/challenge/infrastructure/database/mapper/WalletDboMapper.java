package com.playtomic.challenge.infrastructure.database.mapper;

import com.playtomic.challenge.domain.model.Wallet;
import com.playtomic.challenge.infrastructure.database.model.WalletDbo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AccountDboMapper.class})
public interface WalletDboMapper {

  Wallet toDomain(WalletDbo entity);
  WalletDbo toDbo(Wallet entity);
}
