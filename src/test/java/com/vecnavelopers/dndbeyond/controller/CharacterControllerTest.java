package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.model.Character;
import com.vecnavelopers.dndbeyond.model.ClassSummary;
import com.vecnavelopers.dndbeyond.service.BackgroundService;
import com.vecnavelopers.dndbeyond.service.ClassService;
import com.vecnavelopers.dndbeyond.service.CurrentUserService;
import com.vecnavelopers.dndbeyond.repository.CharacterRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class CharacterControllerTest {

    @Mock
    private BackgroundService backgroundService;

    @Mock
    private ClassService classService;

    @Mock
    private CurrentUserService currentUserService;

    @Mock
    private CharacterRepository characterRepository;

    @Test
    void savingCharacterWhenValidDataTest() {
        when(currentUserService.getCurrentUserId()).thenReturn(1L);

        CharacterController controller = new CharacterController(backgroundService, classService, currentUserService, characterRepository);

        Character character = new Character();
        character.setUserId(1L);

        when(characterRepository.save(any(Character.class))).thenReturn(character);

        String redirectUrl = controller.createCharacter();

        assertNotNull(redirectUrl);
        assertTrue(redirectUrl.contains("/choose-class/character/" + character.getId()));

        verify(characterRepository, times(1)).save(any(Character.class));
    }

    @Test
    void saveCharacterWithUserId() {
        when(currentUserService.getCurrentUserId()).thenReturn(1L);

        CharacterController controller = new CharacterController(backgroundService, classService, currentUserService, characterRepository);

        Character character = new Character();
        character.setUserId(1L);

        when(characterRepository.save(any(Character.class))).thenReturn(character);

        String redirectUrl = controller.createCharacter();

        assertNotNull(redirectUrl);
        assertTrue(redirectUrl.contains("/choose-class/character/" + character.getId()));
        verify(characterRepository).save(any(Character.class));
    }


}
