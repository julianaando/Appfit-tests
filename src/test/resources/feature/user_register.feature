Feature: User registration

  Scenario: User is registered with success
    Given User is new and unknown
    When User is registered with valid data
    And User is authenticated
    Then User is known

    Scenario: User is not registered
    Given User with invalid password
    When User register fail
    Then Login fail