Feature: view a character
Scenario: as a user, i can get a character by name

# Get an adventurer called Lifo
Given url 'http://localhost:8080/Lifo'
When method get
Then status 200
And match response contains {characterName : 'Lifo'}