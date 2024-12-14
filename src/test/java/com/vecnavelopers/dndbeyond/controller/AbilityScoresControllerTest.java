package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.dto.AbilityScoresDto;
import com.vecnavelopers.dndbeyond.model.Character;
import com.vecnavelopers.dndbeyond.repository.CharacterRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest(properties = "spring.flyway.clean-disabled=false")
class AbilityScoresControllerTest {

    @Mock
    CharacterRepository characterRepository;

    @Test
    public void savingScoresWhenSixElementsTest(){
        AbilityScoresController controller = new AbilityScoresController(characterRepository);
        Character character = new Character();
        character.setCharacterStrength(8);
        character.setCharacterDexterity(8);
        character.setCharacterConstitution(8);
        character.setCharacterIntelligence(8);
        character.setCharacterWisdom(8);
        character.setCharacterCharisma(8);

        when(characterRepository.findById(any())).thenReturn(Optional.of(character));

        AbilityScoresDto dto = new AbilityScoresDto(14, 12, 10, 10, 8, 8);


        controller.saveAbilityScores(dto, 1L);

        assertEquals(14, character.getCharacterStrength());
        assertEquals(12, character.getCharacterDexterity());
        assertEquals(10, character.getCharacterConstitution());
        assertEquals(10, character.getCharacterIntelligence());
        assertEquals(8, character.getCharacterWisdom());
        assertEquals(8, character.getCharacterCharisma());

        verify(characterRepository, times(1)).save(character);
    }

}