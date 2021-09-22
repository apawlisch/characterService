package character;

import com.intuit.karate.junit5.Karate;

public class Character {
	// tests create character and get character
	@Karate.Test
	Karate testBrowseAll() {
		return Karate.run("character").relativeTo(getClass());
	}
}


