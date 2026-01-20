Feature: CheckBox selection validation

Scenario: Select Home checkbox
Given user is on CheckBox page
When user selects Home checkbox
Then selected result should contain "home"

Scenario: Select Desktop checkbox
Given user is on CheckBox page
When user selects Desktop checkbox
Then selected result should contain "desktop"

Scenario: Select Documents checkbox
Given user is on CheckBox page
When user selects Documents checkbox
Then selected results should contain "documents"

Scenario: Select multiple checkboxes
Given user is on checkBox page
When user selects Desktop and Documents checkboxes
Then selected result should contain "desktop"
And selected result should contain "documents"

Scenario: Collapse and expand selection persistence
Given user is on CheckBox page
When user selects Home checkbox
And user collapses and expands all
Then selected result should contain "home"


 

