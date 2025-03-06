package voteProject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class VoteApplicationTests {

	@Test
	void contextLoads() {
		double d = 53.2457;
		System.out.println(Math.round(d * 100) / 100.0);
	}

}
