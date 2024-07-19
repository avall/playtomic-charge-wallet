package com.playtomic.challenge.infrastructure.database.repository;

import com.playtomic.challenge.infrastructure.database.model.WalletDbo;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletDbo, UUID> {
//           UPDATE WalletDbo c SET c.balance = c.balance + :amount WHERE c.id = :walletId
  @Modifying
  @Query(
      nativeQuery = true,
      value = """
          update
              public.wallet
          set
              balance = balance + cast(:amount as numeric(38, 2))
          where
              id=:walletId
          """
  )
  void updateBalance(UUID walletId, BigDecimal amount);
}
