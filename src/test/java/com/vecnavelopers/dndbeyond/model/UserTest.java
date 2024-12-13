package com.vecnavelopers.dndbeyond.model;

import com.vecnavelopers.dndbeyond.repository.UserRepository;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(properties = "spring.flyway.clean-disabled=false")
public class UserTest {

    @Autowired
    private Flyway flyway;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUpDatabase() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void insertsNewUser() {
        User user = new User("TestUser");
        user.setAuth0Id("1TestAuth0");
        userRepository.save(user);
    }



}
