Feature: set the initiative of a character
Scenario: as a user, i can change the initiative of a character

# Give adventurer called Lifo an initiative of 10
Given url 'http://localhost:8080/Lifo/initiative/10'
When method put
Then status 200
And match response contains {characterName : 'Lifo', initiative : 10}