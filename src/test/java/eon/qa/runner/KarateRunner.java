package eon.qa.runner;

import com.intuit.karate.junit5.Karate;

class KarateRunner {

	@Karate.Test
	Karate testUsers() {
		return Karate.run("sample").relativeTo(getClass());
	}

	@Karate.Test
	Karate testAll() {
		return Karate.run().relativeTo(getClass());
	}

	@Karate.Test
	Karate testTags() {
		return Karate.run("classpath:").tags("@propertiesfiles").relativeTo(getClass());
	}

}
