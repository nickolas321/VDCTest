Feature: 2021-06-25 01-51 TC1 - Weather - Verify Search Result Is Displayed

  Scenario:  TC1 - Weather - Verify Search Result Is Displayed

	Given I navigate to weather home page
	Then I Search for ho chi minh in Search field on Header of page
	And I verify City Name ho chi minh are matched with search Results
	Then I click on the ho chi minh Result link to see weather information
	And I verify ho chi minh city title matches with selected value