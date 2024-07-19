package com.playtomic.challenge.infrastructure.database.repository;

import com.playtomic.challenge.infrastructure.database.model.AccountDbo;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountDbo, UUID> {
}
