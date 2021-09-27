package viewCharacter;

import com.intuit.karate.junit5.Karate;

public class ViewCharacter {
	@Karate.Test
	Karate testBrowseAll() {
		return Karate.run("createCharacter").relativeTo(getClass());
	}

}
