package traits;

import com.intuit.karate.junit5.Karate;

public class Traits {
	// this tests setting and getting the traits of a character
	@Karate.Test
	Karate testBrowseAll() {
		return Karate.run("traits").relativeTo(getClass());
	}

}
