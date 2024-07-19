package com.playtomic.challenge.infrastructure.database.model;

import com.playtomic.challenge.domain.model.TransactionStatus;
import com.playtomic.challenge.domain.model.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class TransactionDbo {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @JoinColumn(name = "wallet_id", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private WalletDbo wallet;

  @Column(name = "refund_payment_id")
  private String refundPaymentId;

  @Column(name = "payment_id")
  private String paymentId;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private TransactionStatus status;

  @Column(name = "credit_card", nullable = false)
  private String creditCard;

  @Column(name = "type", nullable = false)
  @Enumerated(EnumType.STRING)
  private TransactionType type;

  @Column(name = "amount", nullable = false)
  private BigDecimal amount;

  @CreatedDate
  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;
}
