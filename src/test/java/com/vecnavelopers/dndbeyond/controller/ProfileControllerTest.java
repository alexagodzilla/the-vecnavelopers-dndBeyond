package com.vecnavelopers.dndbeyond.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.vecnavelopers.dndbeyond.model.User;
import com.vecnavelopers.dndbeyond.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.ui.Model;

import java.util.Optional;

class ProfileControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;

    @Mock
    private Model model;

    @InjectMocks
    private ProfileController profileController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);
    }

    @Test
    void testHomePageRedirect_UserExists() {
        // Arrange
        String auth0Id = "auth0|12345";
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setAuth0Id(auth0Id);

        when(authentication.getName()).thenReturn(auth0Id);
        when(userRepository.findByAuth0Id(auth0Id)).thenReturn(Optional.of(mockUser));

        // Act
        String result = profileController.homePageRedirect();

        // Assert
        assertEquals("redirect:/profile/1", result);
    }

    @Test
    void testHomePageRedirect_UserNotExists() {
        // Arrange
        String auth0Id = "auth0|12345";

        when(authentication.getName()).thenReturn(auth0Id);
        when(userRepository.findByAuth0Id(auth0Id)).thenReturn(Optional.empty());

        // Act
        String result = profileController.homePageRedirect();

        // Assert
        assertEquals("redirect:/profile/setup", result);
    }

    @Test
    void testSaveProfile_Success() {
        // Arrange
        String displayName = "TestUser";
        String bio = "Test bio about a D&D persona.";
        MockMultipartFile profilePicture = new MockMultipartFile("profilePicture", "test.jpg", "image/jpeg", "dummy content".getBytes());

        String auth0Id = "auth0|12345";
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setAuth0Id(auth0Id);

        when(authentication.getName()).thenReturn(auth0Id);
        when(userRepository.findByAuth0Id(auth0Id)).thenReturn(Optional.of(mockUser));
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        // Act
        String result = profileController.saveProfile(displayName, bio, profilePicture, model);

        // Assert
        assertEquals("redirect:/profile/1", result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testSaveProfile_EmptyProfilePicture() {
        // Arrange
        String displayName = "TestUser";
        String bio = "Test bio about a D&D persona.";
        MockMultipartFile profilePicture = new MockMultipartFile("profilePicture", "", "", new byte[0]);

        String auth0Id = "auth0|12345";
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setAuth0Id(auth0Id);

        when(authentication.getName()).thenReturn(auth0Id);
        when(userRepository.findByAuth0Id(auth0Id)).thenReturn(Optional.of(mockUser));
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        // Act
        String result = profileController.saveProfile(displayName, bio, profilePicture, model);

        // Assert
        assertEquals("redirect:/profile/1", result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testSaveProfile_Failure() {
        // Arrange
        String displayName = "TestUser";
        String bio = "Test bio about a D&D persona.";
        MockMultipartFile profilePicture = new MockMultipartFile("profilePicture", "test.jpg", "image/jpeg", "dummy content".getBytes());

        String auth0Id = "auth0|12345";

        when(authentication.getName()).thenReturn(auth0Id);
        when(userRepository.findByAuth0Id(auth0Id)).thenThrow(new RuntimeException("Database error"));

        // Act
        String result = profileController.saveProfile(displayName, bio, profilePicture, model);

        // Assert
        assertEquals("profile-setup", result);
        verify(model, times(1)).addAttribute(eq("message"), anyString());
    }

    @Test
    void testShowProfileSetupPage_UserExists() {
        // Arrange
        String auth0Id = "auth0|12345";
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setAuth0Id(auth0Id);

        when(authentication.getName()).thenReturn(auth0Id);
        when(userRepository.findByAuth0Id(auth0Id)).thenReturn(Optional.of(mockUser));

        // Act
        String result = profileController.showProfileSetupPage();

        // Assert
        assertEquals("redirect:/profile/1", result);
    }

    @Test
    void testShowProfileSetupPage_UserNotExists() {
        // Arrange
        String auth0Id = "auth0|12345";

        when(authentication.getName()).thenReturn(auth0Id);
        when(userRepository.findByAuth0Id(auth0Id)).thenReturn(Optional.empty());

        // Act
        String result = profileController.showProfileSetupPage();

        // Assert
        assertEquals("profile-setup", result);
    }
}
