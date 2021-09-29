Feature: alter a characters max hitpoints
Scenario: as a user, i can set the maxHitPoints of a character
# set maxhitpoints of Lifo to 20
Given url 'http://localhost:8080/Lifo/hp/maximum/20'
When method put
Then status 200
And match response contains {characterName : 'Lifo', maxHitPoints : 20}

Scenario: as a user, i can reset the maxHitPoints of a character
# reset maxhitpoints of Lifo to constitution + 5
Given url 'http://localhost:8080/Lifo/hp/maximum'
When method put
Then status 200
And match response contains {characterName : 'Lifo', maxHitPoints : 15}