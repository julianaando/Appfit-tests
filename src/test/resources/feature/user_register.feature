Feature: User registration

  Scenario: User is not registered
    Given User is new and unknown
    When User is registered with valid data
    Then User is registered and can login

    Scenario: User is not registered
    Given User is new and unknown
    When User is registered with invalid data
    Then User is not registered and can't login
