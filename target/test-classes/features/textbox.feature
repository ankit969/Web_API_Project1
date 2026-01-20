Feature: TextBox form validation

Scenario: Valid form submission
Given user is on TextBox page
When user submits form with name "John", email "john@gmail.com", address "Virginia", country "USA"
Then output name should be "John"

Scenario: Empty form submission
Given user is on TextBox page
When user submits empty form
Then output should not be displayed

Scenario: Invalid email submission
Given user is on TextBox page
When user submits form with name "John", email "john@@gmail", address "Virginia", country "USA"
Then email output should not be displayed

Scenario: Special characters in name
Given user is on TextBox page
When user submits form with name "John@123", email "john@gmail.com", address "Virginia", country "USA"
Then output name should be "John@123"

Scenario: Long address submission
Given user is on TextBox page
When user submits form with name "John", email "john@gmail.com", address "<longAddress>", country "USA"
Then long output address should be displayed

