package speed;

import com.intuit.karate.junit5.Karate;

public class Speed {
	// set speed
	@Karate.Test
	Karate testBrowseAll() {
		return Karate.run("speed").relativeTo(getClass());
	}

}
