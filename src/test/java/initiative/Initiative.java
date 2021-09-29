package initiative;

import com.intuit.karate.junit5.Karate;

public class Initiative {
	// set initiative
	@Karate.Test
	Karate testBrowseAll() {
		return Karate.run("initiative").relativeTo(getClass());
	}

}
