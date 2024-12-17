package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.dto.CharacterDetailsDto;
import com.vecnavelopers.dndbeyond.model.Character;
import com.vecnavelopers.dndbeyond.repository.CharacterRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest(properties = "spring.flyway.clean-disabled=false")
class CharacterDetailsControllerTest {

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
        CharacterDetailsController controller = new CharacterDetailsController(characterRepository);
        CharacterDetailsDto dto = new CharacterDetailsDto("TestName", "testUrl", "descriptionTest");
        when(characterRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            controller.saveCharacterDetails(dto, 1L);
        });
    }

    @Test
    public void savingNamePicDescriptionTest(){
        CharacterDetailsController controller = new CharacterDetailsController(characterRepository);
        Character character = new Character();

        when(characterRepository.findById(any())).thenReturn(Optional.of(character));

        CharacterDetailsDto dto = new CharacterDetailsDto("TestName", "testPicUrl", "descriptionTest");

        controller.saveCharacterDetails(dto, 1L);

        assertEquals(dto.getName(), character.getCharacterName());
        assertEquals(dto.getPicUrl(), character.getCharacterPicUrl());
        assertEquals(dto.getDescriprion(), character.getCharacterDescription());

        verify(characterRepository, times(1)).save(character);
    }


}