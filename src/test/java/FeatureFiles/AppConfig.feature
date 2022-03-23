@Login_TCS
Feature: Login TestSuite

  Scenario: Login using valid email and password
    Given As a User I Will Enter user profile
    When I Enter Valid email "test@test.com" and Valid Password "Test1234"
    Then I Should Be Redirected To Home Page


