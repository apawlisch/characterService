package maxHitPoints;

import com.intuit.karate.junit5.Karate;

public class MaxHitPoints {
	// set and reset max hit points
	@Karate.Test
	Karate testBrowseAll() {
		return Karate.run("maxHitPoints").relativeTo(getClass());
	}

}
