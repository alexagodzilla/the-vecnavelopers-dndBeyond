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
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest(properties = "spring.flyway.clean-disabled=false")
class CharacterNameControllerTest {

    @Mock
    CharacterRepository characterRepository;

//    @Autowired
//    private Flyway flyway;
//
//    @BeforeEach
//    void setUpDatabase() {
//        flyway.clean();
//        flyway.migrate();
//    }

    @Test
    public void ThrowsExceptionIfCharacterNotFoundTest() {
        CharacterNameController controller = new CharacterNameController(characterRepository);
        NameAndPicDto dto = new NameAndPicDto("TestName", "testUrl", "descriptionTest");
        when(characterRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            controller.saveNameAndPic(dto, 1L);
        });
    }

    @Test
    public void savingNamePicDescriptionTest(){
        CharacterNameController controller = new CharacterNameController(characterRepository);
        Character character = new Character();

        when(characterRepository.findById(any())).thenReturn(Optional.of(character));

        NameAndPicDto dto = new NameAndPicDto("TestName", "testPicUrl", "descriptionTest");

        controller.saveNameAndPic(dto, 1L);

        assertEquals(dto.getName(), character.getCharacterName());
        assertEquals(dto.getPicUrl(), character.getCharacterPicUrl());
        assertEquals(dto.getDescriprion(), character.getCharacterDescription());

        verify(characterRepository, times(1)).save(character);
    }


}