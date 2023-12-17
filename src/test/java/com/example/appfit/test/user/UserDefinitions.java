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
import org.junit.jupiter.api.Assertions;

public class UserDefinitions {

  private RequestSpecification request = RestAssured.given()
    .baseUri("http://localhost:8080")
    .contentType(ContentType.JSON);
  private Response response = null;

  private User user = new User();

  @Given("User is new and unknown")
  public void userIsNewAndUnknown() {
    user = new User();
    user.setName(RandomStringUtils.randomAlphabetic(20));
    user.setEmail(RandomStringUtils.randomAlphabetic(20));
    user.setPassword("12345@Teste");
  }

  @Given("user is new and without name")
  public void userIsNewAndWithoutName() {
    user = new User();
    user.setEmail(RandomStringUtils.randomAlphabetic(20));
    user.setPassword("12345@Teste");
  }

  @Then("user is not registered and can't login")
  public void userIsNotRegisteredAndCannotLogin() {
    String failReason = response.jsonPath().get("errors[0].document");
    Assertions.assertEquals("must not be null", failReason);
  }

  @And("user remains unknown")
  public void userRemainsUnknown() {
    response = request.when().get("/user?name="+user.getName());
    response.then().statusCode(200);
    List<User> found = response.jsonPath().getList("$");
    Assertions.assertTrue(found.isEmpty());
  }

  @When("User is registered with valid data")
  public void userIsRegisteredWithValidData() {
    response = request.body(user).when().post("/user");
    response.then().statusCode(201);
  }

  @Then("User is registered and can login")
  public void userIsRegisteredAndCanLogin() {
    response = request.when().get("/user?name="+user.getName());
    response.then().statusCode(200);
    String name = response.jsonPath().get("[0].name");
    Assertions.assertEquals(user.getName(), name);
  }

  @When("User is registered with invalid data")
  public void userIsRegisteredWithInvalidData() {
    response = request.body(user).when().post("/user");
    response.then().statusCode(400);
  }

  @Then("User is not registered and can't login")
  public void userIsNotRegisteredAndCanTLogin() {
  }
}
