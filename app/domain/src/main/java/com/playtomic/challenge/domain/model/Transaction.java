package com.playtomic.challenge.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class Transaction {

  private UUID id;
  private String paymentId;
  private String refundPaymentId;
  private @NonNull String creditCard;
  private @NonNull Wallet wallet;
  private @NonNull TransactionStatus status;
  private @NonNull TransactionType type;
  private @NonNull BigDecimal amount;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

}
