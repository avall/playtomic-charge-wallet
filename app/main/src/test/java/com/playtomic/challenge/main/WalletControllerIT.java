package com.playtomic.challenge.main;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WalletControllerIT {

  public static final int BRAND_ID = 1;
  public static final int PRODUCT_ID = 35455;

  @LocalServerPort private int port;

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
  void postChargeTransactionNotFound() {
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

}
