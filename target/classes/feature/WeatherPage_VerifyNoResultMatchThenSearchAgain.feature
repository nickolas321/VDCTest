Feature: 2021-06-25 01-51 TC2 - Weather - Verify Search Result Is Displayed

  Scenario:  TC2 - Weather - Verify Search Result Is Displayed

	Given I navigate to weather home page
	Then I Search for hồ chí minh in Search field on Header of page
	And I verify no results are displayed
	Then I Search for coimbatore in Search field on Header of page
	And I verify City Name coimbatore are matched with search Results
	Then I click on the coimbatore Result link to see weather information
	And I verify coimbatore city title matches with selected value