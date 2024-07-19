package com.playtomic.challenge.infrastructure.database.repository;

import com.playtomic.challenge.infrastructure.database.model.TransactionDbo;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDbo, UUID> {
}
