Feature: set the armorclass of a character
Scenario: as a user, i can change the armorclass of a character

# Give adventurer called Lifo an armorclass of 10
Given url 'http://localhost:8080/Lifo/armorclass/10'
When method put
Then status 200
And match response contains {characterName : 'Lifo', armorClass : 10}