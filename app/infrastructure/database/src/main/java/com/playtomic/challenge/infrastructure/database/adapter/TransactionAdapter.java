package com.playtomic.challenge.infrastructure.database.adapter;

import com.playtomic.challenge.application.port.output.CreateTransactionOutPort;
import com.playtomic.challenge.application.port.output.UpdateTransactionOutPort;
import com.playtomic.challenge.domain.model.Transaction;
import com.playtomic.challenge.infrastructure.database.mapper.TransactionDboMapper;
import com.playtomic.challenge.infrastructure.database.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionAdapter implements CreateTransactionOutPort,
    UpdateTransactionOutPort {

  private final TransactionRepository repository;
  private final TransactionDboMapper mapper;

  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public Transaction createTransaction(Transaction transaction) {
    return mapper.toDomain(repository.save(mapper.toDbo(transaction)));
  }

  /**
   * Update the transaction.
   * @param transaction transaction
   * @return Transaction
   */
  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public Transaction updateTransaction(Transaction transaction) {
    return mapper.toDomain(repository.save(mapper.toDbo(transaction)));

  }
}
