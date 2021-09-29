Feature: set the traits of a character
Scenario: as a user, i can change the traits of a character

# Give adventurer called Armorman traits
Given url 'http://localhost:8080/Armorman/traits'
And request {charisma : '5', constitution : '5', dexterity : '5', intelligence : '5', strength : '5', wisdom : '5'}
When method put
Then status 200
And match response contains {characterName : 'Armorman', charisma : 5, constitution : 5, dexterity : 5, intelligence : 5, strength : 5, wisdom : 5}