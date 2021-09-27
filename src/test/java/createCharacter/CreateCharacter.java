package createCharacter;

import com.intuit.karate.junit5.Karate;

public class CreateCharacter {
	// tests create character
	@Karate.Test
	Karate testBrowseAll() {
		return Karate.run("createCharacter").relativeTo(getClass());
	}
}


