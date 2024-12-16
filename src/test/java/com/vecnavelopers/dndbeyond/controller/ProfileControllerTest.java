package com.vecnavelopers.dndbeyond.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.vecnavelopers.dndbeyond.controller.ProfileController;
import com.vecnavelopers.dndbeyond.model.User;
import com.vecnavelopers.dndbeyond.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;

class ProfileControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Model model;

    @InjectMocks
    private ProfileController profileController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProfile_Success() {
        // Arrange
        String displayName = "TestUser";
        String bio = "Test bio about a D&D persona.";
        MockMultipartFile profilePicture = new MockMultipartFile("profilePicture", "test.jpg", "image/jpeg", "dummy content".getBytes());

        User mockUser = new User();
        mockUser.setDisplayName(displayName);
        mockUser.setBio(bio);
        mockUser.setProfilePicture("test.jpg");

        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        // Act
        String result = profileController.saveProfile(displayName, bio, profilePicture, model);

        // Assert
        assertEquals("redirect:/profile/success", result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testSaveProfile_EmptyProfilePicture() {
        // Arrange
        String displayName = "TestUser";
        String bio = "Test bio about a D&D persona.";
        MockMultipartFile profilePicture = new MockMultipartFile("profilePicture", "", "", new byte[0]);

        User mockUser = new User();
        mockUser.setDisplayName(displayName);
        mockUser.setBio(bio);
        mockUser.setProfilePicture(null);

        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        // Act
        String result = profileController.saveProfile(displayName, bio, profilePicture, model);

        // Assert
        assertEquals("redirect:/profile/success", result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testSaveProfile_Failure() {
        // Arrange
        String displayName = "TestUser";
        String bio = "Test bio about a D&D persona.";
        MockMultipartFile profilePicture = new MockMultipartFile("profilePicture", "test.jpg", "image/jpeg", "dummy content".getBytes());

        doThrow(new RuntimeException("Database error")).when(userRepository).save(any(User.class));

        // Act
        String result = profileController.saveProfile(displayName, bio, profilePicture, model);

        // Assert
        assertEquals("profile-setup", result);
        verify(userRepository, times(1)).save(any(User.class));
        verify(model, times(1)).addAttribute(eq("message"), anyString());
    }
}

