package com.playtomic.challenge.infrastructure.api.mapper;

import com.playtomic.challenge.api.lib.model.WalletResponseDto;
import com.playtomic.challenge.domain.model.Wallet;
import java.util.UUID;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface WalletDtoMapper {
  WalletResponseDto toDto(Wallet entity);
  Wallet toDomain(UUID id);
}
