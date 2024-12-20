package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.dto.CharacterDetailsDto;
import com.vecnavelopers.dndbeyond.model.Character;
import com.vecnavelopers.dndbeyond.repository.CharacterRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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

//    @Test
//    public void ThrowsExceptionIfCharacterNotFoundTest() {
//        CharacterDetailsController controller = new CharacterDetailsController(characterRepository);
//        CharacterDetailsDto dto = new CharacterDetailsDto("TestName", "testUrl", "descriptionTest");
//        when(characterRepository.findById(any())).thenReturn(Optional.empty());
//
//        assertThrows(RuntimeException.class, () -> {
//            controller.saveCharacterDetails(dto, 1L);
//        });
//    }
    @Test
    public void ThrowsExceptionIfCharacterNotFoundTest() throws IOException {
        // Arrange
        CharacterDetailsController controller = new CharacterDetailsController(characterRepository);

        // Create a mock MultipartFile for the file upload (optional for this test, but we pass it for consistency)
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.getOriginalFilename()).thenReturn("testImage.png");
        InputStream mockInputStream = new ByteArrayInputStream("dummy data".getBytes());
        when(mockFile.getInputStream()).thenReturn(mockInputStream);
        when(mockFile.isEmpty()).thenReturn(false);

        // When the character repository returns an empty Optional
        when(characterRepository.findById(any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> {
            controller.saveCharacterDetails("TestName", "descriptionTest", mockFile, 1L);
        });
    }


//    @Test
//    public void savingNamePicDescriptionTest(){
//        CharacterDetailsController controller = new CharacterDetailsController(characterRepository);
//        Character character = new Character();
//
//        when(characterRepository.findById(any())).thenReturn(Optional.of(character));
//
//        CharacterDetailsDto dto = new CharacterDetailsDto("TestName", "testPicUrl", "descriptionTest");
//
//        controller.saveCharacterDetails(dto, 1L);
//
//        assertEquals(dto.getName(), character.getCharacterName());
//        assertEquals(dto.getPicUrl(), character.getCharacterPicUrl());
//        assertEquals(dto.getDescriprion(), character.getCharacterDescription());
//
//        verify(characterRepository, times(1)).save(character);
//    }
    @Test
    public void savingNamePicDescriptionTest() throws IOException {
        // Arrange
        CharacterDetailsController controller = new CharacterDetailsController(characterRepository);
        Character character = new Character();

        // Mock the repository to return the character when it's fetched by ID
        when(characterRepository.findById(any())).thenReturn(Optional.of(character));

        // Mock a MultipartFile for the picture upload
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.getOriginalFilename()).thenReturn("testPicUrl");
        InputStream mockInputStream = new ByteArrayInputStream("dummy data".getBytes());
        when(mockFile.getInputStream()).thenReturn(mockInputStream);
        when(mockFile.isEmpty()).thenReturn(false);

        // Act: Call the controller with the name, description, and file
        controller.saveCharacterDetails("TestName", "descriptionTest", mockFile, 1L);

        // Assert: Verify the character details were updated
        assertEquals("TestName", character.getCharacterName());
        assertEquals("testPicUrl", character.getCharacterPicUrl());  // Check that the URL is set correctly
        assertEquals("descriptionTest", character.getCharacterDescription());

        // Verify that the repository's save method was called exactly once
        verify(characterRepository, times(1)).save(character);
    }



}