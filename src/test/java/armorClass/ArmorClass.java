package armorClass;

import com.intuit.karate.junit5.Karate;

public class ArmorClass {
	// this has tests for setting and getting the armor class
	@Karate.Test
	Karate testBrowseAll() {
		return Karate.run("armorClass").relativeTo(getClass());
	}
}
