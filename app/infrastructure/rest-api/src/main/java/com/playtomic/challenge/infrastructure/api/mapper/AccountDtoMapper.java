package com.playtomic.challenge.infrastructure.api.mapper;

import com.playtomic.challenge.api.lib.model.AccountCreateRequestDto;
import com.playtomic.challenge.api.lib.model.AccountResponseDto;
import com.playtomic.challenge.domain.model.Account;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountDtoMapper {
  AccountResponseDto toDto(Account entity);
  List<AccountResponseDto> toDto(List<Account> entity);
  Account toDomain(AccountCreateRequestDto entity);
}
