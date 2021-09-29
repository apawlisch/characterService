Feature: create a character
# response codes should be 201, methods are post

Scenario: as a user, i can create a character using only a name
Given url 'http://localhost:8080'
And request {characterName : 'Test1'}
When method post
Then status 201
And match response contains {characterName : 'Test1'}

Scenario: as a user, i can create a character using a name and traits
Given url 'http://localhost:8080'
And request {characterName : 'Test2', charisma : '5', constitution : '5', dexterity : '5', intelligence : '5', strength : '5', wisdom : '5'}
When method post
Then status 201
And match response contains {characterName : 'Test2', charisma : 5, constitution : 5, dexterity : 5, intelligence : 5, strength : 5, wisdom : 5}


Scenario: as a user, i can create a character using a name and armorclass
Given url 'http://localhost:8080'
And request {characterName : 'Test3', armorClass : '10'}
When method post
Then status 201
And match response contains {characterName : 'Test3', armorClass : 10}


Scenario: as a user, i can create a character using a name, traits, and armorclass
Given url 'http://localhost:8080'
And request {characterName : 'Test4', armorClass : '10', charisma : '5', constitution : '5', dexterity : '5', intelligence : '5', strength : '5', wisdom : '5'}
When method post
Then status 201
And match response contains {characterName : 'Test4', armorClass : 10, charisma : 5, constitution : 5, dexterity : 5, intelligence : 5, strength : 5, wisdom : 5}
