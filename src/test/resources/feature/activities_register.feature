Feature: Activities register

  Scenario: Exercise linked to a User
    Given Exercise and User are known
    When Activities are registered successfully
    Then User should have the Exercise registered

  Scenario: Failure to register activities for an unknown User
    Given the Exercise is known
    And the User is unknown
    When Activities are registered successfully
    Then the registration should fail
    And an error message should be displayed
