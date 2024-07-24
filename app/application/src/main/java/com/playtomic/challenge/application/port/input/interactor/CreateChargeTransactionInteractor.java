package com.playtomic.challenge.application.port.input.interactor;

import com.playtomic.challenge.application.exception.PaymentGatewayException;
import com.playtomic.challenge.application.port.input.ChargeCreditCardUseCase;
import com.playtomic.challenge.application.port.input.CreateChargeTransactionUseCase;
import com.playtomic.challenge.application.port.input.FindWalletByIdUseCase;
import com.playtomic.challenge.application.port.input.UpdateTransactionUseCase;
import com.playtomic.challenge.application.port.input.UpdateWalletBalanceUseCase;
import com.playtomic.challenge.application.port.output.CreateTransactionOutPort;
import com.playtomic.challenge.domain.decorator.Interactor;
import com.playtomic.challenge.domain.model.Payment;
import com.playtomic.challenge.domain.model.TransactionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation for the Use Case
 */
@Slf4j
@Interactor
@RequiredArgsConstructor
public class CreateChargeTransactionInteractor
    implements CreateChargeTransactionUseCase {

  private final CreateTransactionOutPort createTransactionOutPort;
  private final UpdateWalletBalanceUseCase updateWalletBalanceUseCase;
  private final ChargeCreditCardUseCase chargeCreditCardUseCase;
  private final UpdateTransactionUseCase updateTransactionUseCase;
  private final FindWalletByIdUseCase findWalletByIdUseCase;

  /**
   * Method to execute the Use Case to create Account
   * @param input parameters
   * @return Account
   */
  @Override
  @Transactional
  public OutputValues execute(InputValues input) {
    var transaction = input.getTransaction();

    // Check wallet exists. If Not exists, nothing to do.
    findWalletByIdUseCase.execute(
        FindWalletByIdUseCase.InputValues.builder().id(transaction.getWallet().id()).build());

    // Save transaction in status PENDING
    transaction = createTransactionOutPort.createTransaction(transaction);

    try {
        // Charge credit card.
        Payment payment = chargeCreditCardUseCase.execute(
                ChargeCreditCardUseCase.InputValues.builder()
                    .creditCardNumber(transaction.getCreditCard())
                    .amount(transaction.getAmount()).build()).getPayment();
        log.info("Payment successful: {}", payment);

        // Update balance
        updateWalletBalanceUseCase.execute(
            UpdateWalletBalanceUseCase.InputValues.builder().amount(transaction.getAmount())
                .id(transaction.getWallet().id()).build());
        log.info("Update balance successful: {}", payment);

        // update transaction & change status to COMPLETED
        transaction.setStatus(TransactionStatus.COMPLETED);
        transaction.setPaymentId(payment.id());
        updateTransactionUseCase.execute(UpdateTransactionUseCase.InputValues.builder()
                    .transaction(transaction).build());

    } catch (PaymentGatewayException e) {
      // change status to CHARGE_FAILURE
      transaction.setStatus(TransactionStatus.CHARGE_FAILURE);
      updateTransactionUseCase.execute(UpdateTransactionUseCase.InputValues.builder()
              .transaction(transaction).build());
      throw e;
    } catch (Exception e) {
      // change status to UNKNOWN_FAILURE
      transaction.setStatus(TransactionStatus.UNKNOWN_FAILURE);
      updateTransactionUseCase.execute(UpdateTransactionUseCase.InputValues.builder()
                  .transaction(transaction).build());
      throw e;
    }

    return OutputValues.builder().transaction(transaction).build();
  }
}
