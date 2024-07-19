package com.playtomic.challenge.infrastructure.database.adapter;

import com.playtomic.challenge.application.port.output.CreateAccountOutPort;
import com.playtomic.challenge.application.port.output.FindAllAccountsOutPort;
import com.playtomic.challenge.domain.model.Account;
import com.playtomic.challenge.infrastructure.database.mapper.AccountDboMapper;
import com.playtomic.challenge.infrastructure.database.repository.AccountRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountAdapter implements CreateAccountOutPort, FindAllAccountsOutPort {

  private final AccountRepository repository;
  private final AccountDboMapper mapper;

  @Override
  public Account createAccount(Account account) {
    return mapper.toDomain(repository.save(mapper.toDbo(account)));
  }

  @Override
  public List<Account> findAll() {
    return mapper.toDomain(repository.findAll());
  }
}
