Feature: set the speed of a character

Scenario: as a user, i can change the speed of a character
# Give adventurer called Lifo a speed of 10
Given url 'http://localhost:8080/Lifo/speed/10'
When method put
Then status 200
And match response contains {characterName : 'Lifo', speed : 10}

Scenario: as a user, i can reset the speed of a character
# Give adventurer called Lifo a speed based on their traits. Lifo Dexterity + 25
Given url 'http://localhost:8080/Lifo/speed'
When method put
Then status 200
And match response contains {characterName : 'Lifo', speed : 35}