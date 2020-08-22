package com.bidding.system;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BiddingSystemApplicationTest {

	@Test
	public void contextLoads() {
		BiddingSystemApplication.main(new String[] {});

		// Ran successfully if it gets here...
		Assertions.assertTrue(true);
	}

}
