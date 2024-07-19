package com.playtomic.challenge.infrastructure.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.playtomic.challenge.application.error.CommonErrorMessage;
import com.playtomic.challenge.application.exception.PaymentGatewayException;
import com.playtomic.challenge.application.port.output.ChargeCreditCardOutPort;
import com.playtomic.challenge.application.port.output.RefundPaymentOutPort;
import com.playtomic.challenge.domain.model.Payment;
import com.playtomic.challenge.infrastructure.client.dto.PaymentDto;
import com.playtomic.challenge.infrastructure.client.exception.StripeServiceException;
import com.playtomic.challenge.infrastructure.client.handler.StripeRestTemplateResponseErrorHandler;
import java.math.BigDecimal;
import java.net.URI;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.zalando.logbook.spring.LogbookClientHttpRequestInterceptor;

/**
 * Handles the communication with Stripe.
 *
 * <p>A real implementation would call to String using their API/SDK. This dummy implementation
 * throws an error when trying to charge less than 10€.
 */
@Service
public class StripeAdapter implements ChargeCreditCardOutPort, RefundPaymentOutPort {

  @NonNull
  private final URI chargesUri;

  @NonNull
  private final URI refundsUri;

  @NonNull
  private final RestTemplate restTemplate;

  public StripeAdapter(@Value("${stripe.simulator.charges-uri}") @NonNull URI chargesUri,
      @Value("${stripe.simulator.refunds-uri}") @NonNull URI refundsUri,
      @NonNull RestTemplateBuilder restTemplateBuilder,
      @NonNull LogbookClientHttpRequestInterceptor logbookClientHttpRequestInterceptor) {

//    final HttpClient httpClient = HttpClientBuilder.create()
//        .setConnectionManager(new PoolingHttpClientConnectionManager()).build();

    this.chargesUri = chargesUri;
    this.refundsUri = refundsUri;
    this.restTemplate =
        restTemplateBuilder
            .additionalInterceptors(logbookClientHttpRequestInterceptor)
            .errorHandler(new StripeRestTemplateResponseErrorHandler())
//            .requestFactory(() -> new BufferingClientHttpRequestFactory(
//                new HttpComponentsClientHttpRequestFactory(httpClient)))
            .build();
  }

  /**
   * Charges money in the credit card.
   *
   * Ignore the fact that no CVC or expiration date are provided.
   *
   * @param creditCardNumber The number of the credit card
   * @param amount The amount that will be charged.
   *
   * @throws StripeServiceException
   */
  @Override
  public Payment charge(@NonNull String creditCardNumber, @NonNull BigDecimal amount) {
    try {
      ChargeRequest body = new ChargeRequest(creditCardNumber, amount);
      PaymentDto dto = restTemplate.postForObject(chargesUri, body, PaymentDto.class);
      assert dto != null;
      return Payment.builder()
          .id(dto.getId())
          .build();
    } catch (Exception e) {
      throw new PaymentGatewayException(e.getMessage(),
          CommonErrorMessage.PAYMENT_GATEWAY_ERROR.getCode());
    }
  }

  /**
   * Refunds the specified payment.
   */
  @Override
  public void refund(@NonNull String paymentId) throws StripeServiceException {
    // Object.class because we don't read the body here.
    try {
      restTemplate.postForEntity(refundsUri.toString(), null, Object.class, paymentId);
    } catch (Exception e) {
      throw new PaymentGatewayException(e.getMessage(),
          CommonErrorMessage.PAYMENT_GATEWAY_ERROR.getCode());
    }
  }

  @AllArgsConstructor
  private static class ChargeRequest {

    @NonNull
    @JsonProperty("credit_card")
    String creditCardNumber;

    @NonNull
    @JsonProperty("amount")
    BigDecimal amount;
  }}
