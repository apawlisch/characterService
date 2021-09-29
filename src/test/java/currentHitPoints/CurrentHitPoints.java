package currentHitPoints;

import com.intuit.karate.junit5.Karate;

public class CurrentHitPoints {
	// this tests adding HP, subtracting HP, and restoring HP to max
	@Karate.Test
	Karate testBrowseAll() {
		return Karate.run("currentHitPoints").relativeTo(getClass());
	}

}
