package com.example.appfit.test.user;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Assertions;

public class UserDefinitions {

  private RequestSpecification request = RestAssured.given()
    .baseUri("http://localhost:8080")
    .contentType(ContentType.JSON);
  private Response response = null;
  private User user = new User();
  private String token;

  @Given("User is new and unknown")
  public void userIsNewAndUnknown() {
    user = new User();
    user.setName(RandomStringUtils.randomAlphabetic(20));
    user.setEmail(RandomStringUtils.randomAlphabetic(10)+"@teste.com");
    user.setPassword("12345@Teste");
  }

  @When("User is registered with valid data")
  public void userIsRegisteredWithValidData() {
    response = request.body(user).when().post("/user");
    response.then().statusCode(201);
    user.setId(response.jsonPath().get("id"));
  }

  @And("User is authenticated")
  public void userIsAuthenticated() {
    LoginRequest loginRequest = new LoginRequest();
    loginRequest.setEmail(user.getEmail());
    loginRequest.setPassword(user.getPassword());

    response = request.body(loginRequest).when().post("/login");
    response.then().statusCode(200);
    token = response.jsonPath().get("token");
  }

  @Then("User is known")
  public void userIsKnown() {
    request = RestAssured.given().header(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    response = request.when().get("/user/" + user.getId());
    response.then().statusCode(200);
    String name = response.jsonPath().get("name");
    Assertions.assertEquals(user.getName(), name);
  }

  @Given("User with invalid password")
  public void userWithInvalidPassword() {
    user = new User();
    user.setName(RandomStringUtils.randomAlphabetic(20));
    user.setEmail(RandomStringUtils.randomAlphabetic(10)+"@teste.com");
    user.setPassword(RandomStringUtils.randomNumeric(1));
  }

  @When("User register fail")
  public void userRegisterFail() {
    response = request.body(user).when().post("/user");
    response.then().statusCode(400);
    Assertions.assertEquals("Senha fora do padrão", response.getBody().asString());
  }

  @Then("Login fail")
  public void loginFail() {
    LoginRequest loginRequest = new LoginRequest();
    loginRequest.setEmail(user.getEmail());
    loginRequest.setPassword(user.getPassword());

    response = request.body(loginRequest).when().post("/login");
    response.then().statusCode(403);
  }
}
