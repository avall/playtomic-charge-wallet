package com.playtomic.challenge.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder
public record Wallet(UUID id,
                     BigDecimal balance,
                     LocalDateTime createdAt,
                     LocalDateTime updatedAt) {}
