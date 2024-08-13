package com.playtomic.challenge.main;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import com.github.tomakehurst.wiremock.http.Request;
import static io.restassured.RestAssured.given;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class WalletControllerIT {

  public static final int BRAND_ID = 1;
  public static final int PRODUCT_ID = 35455;

  private static WireMockServer mockServer;
  @LocalServerPort private int port;

  private static void requestReceived(
      Request inRequest, com.github.tomakehurst.wiremock.http.Response inResponse) {
    log.info("WireMock request at URL: {}", inRequest.getAbsoluteUrl());
    log.info("WireMock request headers: \n{}", inRequest.getHeaders());
    log.info("WireMock response body: \n{}", inResponse.getBodyAsString());
    log.info("WireMock response headers: \n{}", inResponse.getHeaders());
  }

  @BeforeAll
  public static void beforeAll() {
    mockServer = new WireMockServer(9999);
    WireMock.configureFor("localhost", 9999);
    mockServer.addMockServiceRequestListener(WalletControllerIT::requestReceived);
    mockServer.start();
  }

  @AfterAll
  public static void afterAll() {
    mockServer.stop();
  }


  @Test
  void postAccountOk() {
    given()
        .body("""
                  {
                      "alias": "Jandrinet",
                      "email": "alex.vall.mainou@gmail.com",
                      "first_name": "alex",
                      "last_name": "vall"
                  }
            """)
        .contentType("application/json")
        .port(port)
        .when()
        .post("/v1/accounts")
        .then()
        .statusCode(201)
        .contentType("application/json");
  }

  @Test
  void postAccount400() {
    given()
        .body("""
                  {
                      "alias": "Jandrinet",
                      "first_name": "alex",
                      "last_name": "vall"
                  }
            """)
        .contentType("application/json")
        .port(port)
        .when()
        .post("/v1/accounts")
        .then()
        .statusCode(400)
        .contentType("application/json");
  }

  @Test
  void getAccounts() {
    given()
        .port(port)
        .when()
        .get("/v1/accounts")
        .then()
        .statusCode(200)
        .contentType("application/json");
  }

  @Test
  void getWalletById() {
    given()
        .port(port)
        .when()
        .get("/v1/wallets/033e4ff1-c5ce-465f-ac51-1d167398bb84")
        .then()
        .statusCode(200)
        .contentType("application/json");
  }

  @Test
  void getWalletNotFound() {
    given()
        .port(port)
        .when()
        .get("/v1/wallets/033e4ff1-c5ce-465f-ac51-1d167398bbbb")
        .then()
        .statusCode(404)
        .contentType("application/json");
  }

  @Test
  void postChargeTransactionOk() {
    mockServer.stubFor(
        WireMock.post(urlPathMatching("/v1/stripe-simulator/charges"))
            .willReturn(
                aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody(
                        """ 
                                  {
                                      "id": "31fbc2c9-25dc-4a4a-a790-019caee4ced8"
                                  }
                            """)));

    given()
        .body("""
                {
                  "wallet_id" : "033e4ff1-c5ce-465f-ac51-1d167398bb84",
                  "credit_card": "4242424242424242",
                  "amount": 100.00,
                  "type": "TOPUP"
                }
            """)
        .contentType("application/json")
        .port(port)
        .when()
        .post("/v1/transactions")
        .then()
        .statusCode(201)
        .contentType("application/json");
  }

  @Test
  void postChargeTransactionWhenWalletNotFound() {
    given()
        .body("""
                {
                  "wallet_id" : "033e4ff1-c5ce-465f-ac51-1d167398bbbb",
                  "credit_card": "4242424242424242",
                  "amount": 100.00,
                  "type": "TOPUP"
                }
            """)
        .contentType("application/json")
        .port(port)
        .when()
        .post("/v1/transactions")
        .then()
        .statusCode(404)
        .contentType("application/json");
  }

  @Test
  void postChargeTransaction422() {
    mockServer.stubFor(
        WireMock.post(urlPathMatching("/v1/stripe-simulator/charges"))
            .willReturn(
                aResponse()
                    .withStatus(422)));

    given()
        .body("""
                {
                  "wallet_id" : "033e4ff1-c5ce-465f-ac51-1d167398bb84",
                  "credit_card": "4242424242424242",
                  "amount": 1.00,
                  "type": "TOPUP"
                }
            """)
        .contentType("application/json")
        .port(port)
        .when()
        .post("/v1/transactions")
        .then()
        .statusCode(400)
        .contentType("application/json");
  }


}
