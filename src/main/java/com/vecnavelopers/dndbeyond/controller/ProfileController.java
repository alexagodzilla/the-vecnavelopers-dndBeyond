package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.model.User;
import com.vecnavelopers.dndbeyond.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    private String getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated.");
        }
        return authentication.getName(); // Get the username/ID directly from the principal
    }

    @GetMapping("/")
    public String homePageRedirect() {
        try {
            String auth0Id = getAuthenticatedUserId();
            // Check if the user exists in the database
            return userRepository.findByAuth0Id(auth0Id)
                    .map(user -> "redirect:/profile/" + user.getId()) // Redirect to profile page
                    .orElse("redirect:/profile/setup"); // Redirect to setup page if user doesn't exist
        } catch (IllegalStateException e) {
            // Handle unauthenticated access gracefully
            return "redirect:/login";
        }
    }

    @GetMapping("/profile/{userId}")
    public ModelAndView viewProfile(@PathVariable Long userId) {
        String auth0Id = getAuthenticatedUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getAuth0Id().equals(auth0Id)) {
            throw new IllegalStateException("Unauthorized access to profile.");
        }

    @GetMapping("/profile/{userId}")
    public ModelAndView viewProfile(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ModelAndView profilePage = new ModelAndView("profile-page");
        profilePage.addObject("user", user);

        return profilePage;
    }

    @GetMapping("/profile/setup")
    public String showProfileSetupPage() {
        String auth0Id = getAuthenticatedUserId();
        boolean userExists = userRepository.findByAuth0Id(auth0Id).isPresent();
        if (userExists) {
            User existingUser = userRepository.findByAuth0Id(auth0Id).orElseThrow();
            return "redirect:/profile/" + existingUser.getId();
        }
        return "profile-setup";
    }

    @PostMapping("/profile/setup")
    public String saveProfile(@RequestParam("displayName") String displayName,
                              @RequestParam("bio") String bio,
                              @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture,
                              Model model) {
        try {
            String auth0Id = getAuthenticatedUserId();

            User user = userRepository.findByAuth0Id(auth0Id).orElse(new User());
            user.setAuth0Id(auth0Id);
            user.setDisplayName(displayName);
            user.setBio(bio);

            if (profilePicture != null && !profilePicture.isEmpty()) {
                String fileName = profilePicture.getOriginalFilename();
                user.setProfilePicture(fileName);
                System.out.println("Uploaded file: " + fileName);
            }

            userRepository.save(user);
            System.out.println("Profile updated successfully!");

            return "redirect:/profile/" + user.getId(); // Redirect to the user's profile page
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "An error occurred while updating your profile.");
            return "profile-setup"; // Return to the profile setup page with an error message
        }
    }

    @GetMapping("/profile/success")
    public String showSuccessPage() {
        return "profile-success";
    }


    @GetMapping("/profile/edit")
    public String showEditProfilePage(Model model) {
        String auth0Id = getAuthenticatedUserId();

        User user = userRepository.findByAuth0Id(auth0Id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);

        return "edit-profile";
    }

    @PostMapping("/profile/edit")
    public String saveEditedProfile(@RequestParam("displayName") String displayName,
                                    @RequestParam("bio") String bio,
                                    @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture,
                                    Model model) {
        try {
            String auth0Id = getAuthenticatedUserId();

            User user = userRepository.findByAuth0Id(auth0Id)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            user.setDisplayName(displayName);
            user.setBio(bio);

            if (profilePicture != null && !profilePicture.isEmpty()) {
                String fileName = profilePicture.getOriginalFilename();
                user.setProfilePicture(fileName);
                System.out.println("Uploaded new file: " + fileName);
            }

            userRepository.save(user);
            return "redirect:/profile/" + user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "An error occurred while saving your profile.");
            return "edit-profile";
        }
    }

}

}

