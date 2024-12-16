package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.dto.AbilityScoresDto;
import com.vecnavelopers.dndbeyond.dto.NameAndPicDto;
import com.vecnavelopers.dndbeyond.model.Character;
import com.vecnavelopers.dndbeyond.repository.CharacterRepository;
import com.vecnavelopers.dndbeyond.repository.UserRepository;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest(properties = "spring.flyway.clean-disabled=false")
class CharacterNameControllerTest {

    @Mock
    CharacterRepository characterRepository;

    @Autowired
    private Flyway flyway;

    @BeforeEach
    void setUpDatabase() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void ThrowsExceptionIfCharacterNotFoundTest() {
        CharacterNameController controller = new CharacterNameController(characterRepository);
        NameAndPicDto dto = new NameAndPicDto("TestName", "testUrl");
        when(characterRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            controller.saveNameAndPic(dto, 1L);
        });
    }


}