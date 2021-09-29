Feature: alter the currentHitPoints of an adventurer

Scenario: as a user, i can set the currentHitPoints of a character to their maxHitPoints
# set hitpoints of Lifot to max, the test assumes maxHP is 15
Given url 'http://localhost:8080/Lifo/hp'
When method put
Then status 200
And match response contains {characterName : 'Lifo', currentHitPoints : 15}

Scenario: as a user, i can subtract from the currentHitPoints of a character
# subtract 5 hitpoints from Lifo
Given url 'http://localhost:8080/Lifo/hp/subtraction/5'
When method put
Then status 200
And match response contains {characterName : 'Lifo', currentHitPoints : 10}

Scenario: as a user, i can add to the currentHitPoints of a character
# add 5 hitpoints to Lifo
Given url 'http://localhost:8080/Lifo/hp/addition/5'
When method put
Then status 200
And match response contains {characterName : 'Lifo', currentHitPoints : 15}






