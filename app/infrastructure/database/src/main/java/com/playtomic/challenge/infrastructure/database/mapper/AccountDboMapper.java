package com.playtomic.challenge.infrastructure.database.mapper;

import com.playtomic.challenge.domain.model.Account;
import com.playtomic.challenge.infrastructure.database.model.AccountDbo;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountDboMapper {

  Account toDomain(AccountDbo entity);
  List<Account> toDomain(List<AccountDbo> entity);
  AccountDbo toDbo(Account entity);
}
