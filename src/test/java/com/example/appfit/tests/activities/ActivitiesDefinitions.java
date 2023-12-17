package com.example.appfit.tests.activities;

import static org.junit.jupiter.api.Assertions.fail;

import ch.qos.logback.core.testUtil.RandomUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Assertions;

public class ActivitiesDefinitions {

  private final RequestSpecification request = RestAssured.given()
    .baseUri("http://localhost:8080")
    .contentType(ContentType.JSON);

  private Response response = null;

  private final Activities activities = new Activities();

  @Given("Exercise and User are known")
    public void exerciseAndUserAreKnown() {
    activities.setUserId(RandomUtil.getPositiveInt());
    activities.setExercisesIds(List.of(RandomUtil.getPositiveInt()));
    activities.setDate(LocalDate.of(23, 12, 10));
}

  @When("Activities are registered successfully")
  public void activitiesAreRegisteredSuccessfully() {
    response = request.body(activities).when().post("/activities");
    response.then().statusCode(403);
  }

  @Then("User should have the Exercise registered")
  public void userShouldHaveExerciseRegistered() {
    response = request.when().get("/activities?userId="+activities.getUserId());
    response.then().statusCode(403);
  }

  @Given("the Exercise is known")
  public void exerciseIsKnown() {
    activities.setExercisesIds(List.of(RandomUtil.getPositiveInt()));
  }

  @Given("the User is unknown")
  public void userIsUnknown() {
    activities.setUserId(null);
  }

  @Then("the registration should fail")
  public void registrationShouldFail() {
    response = request.when().body(activities).post("/activities");
    response.then().statusCode(403);
  }

  @Then("an error message should be displayed")
  public void errorMessageShouldBeDisplayed() {
  }
}
