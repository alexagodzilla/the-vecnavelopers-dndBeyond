package com.vecnavelopers.dndbeyond;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(properties = "spring.flyway.clean-disabled=false")
class DndbeyondApplicationTests {

	@Autowired
	private Flyway flyway;

	@BeforeEach
	void setUpDatabase() {
		flyway.clean();
		flyway.migrate();
	}

	@Test
	void contextLoads() {
	}

}
