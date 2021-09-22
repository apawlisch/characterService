package hitPoints;

import com.intuit.karate.junit5.Karate;

public class HitPoints {
	// this tests adding HP, subtracting HP, and restoring HP to max
	@Karate.Test
	Karate testBrowseAll() {
		return Karate.run("hitPoints").relativeTo(getClass());
	}

}
