package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.model.Character;
import com.vecnavelopers.dndbeyond.model.User;
import com.vecnavelopers.dndbeyond.repository.CharacterRepository;
import com.vecnavelopers.dndbeyond.repository.UserRepository;
import org.springframework.beans.BeanUtils;
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

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CharacterRepository characterRepository;

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
        public ModelAndView viewProfile (@PathVariable Long userId){
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            ModelAndView profilePage = new ModelAndView("profile-page");
            profilePage.addObject("user", user);

            return profilePage;
        }

        @GetMapping("/profile/setup")
        public String showProfileSetupPage () {
            String auth0Id = getAuthenticatedUserId();
            boolean userExists = userRepository.findByAuth0Id(auth0Id).isPresent();
            if (userExists) {
                User existingUser = userRepository.findByAuth0Id(auth0Id).orElseThrow();
                return "redirect:/profile/" + existingUser.getId();
            }
            return "profile-setup";
        }

        @PostMapping("/profile/setup")
        public String saveProfile (@RequestParam("displayName") String displayName,
                @RequestParam("bio") String bio,
                @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture,
                Model model){
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
        public String showSuccessPage () {
            return "profile-success";
        }


        @GetMapping("/profile/edit")
        public String showEditProfilePage (Model model){
            String auth0Id = getAuthenticatedUserId();

            User user = userRepository.findByAuth0Id(auth0Id)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            model.addAttribute("user", user);

            return "edit-profile";
        }

        @PostMapping("/profile/edit")
        public String saveEditedProfile (@RequestParam("displayName") String displayName,
                @RequestParam("bio") String bio,
                @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture,
                Model model){
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

    @GetMapping("/all-characters")
    public String getUserCharacters(Model model) {
        // Step 1: Retrieve authenticated user's Auth0 ID
        String auth0Id = SecurityContextHolder.getContext().getAuthentication().getName();

        // Step 2: Find the user and handle missing users gracefully
        User currentUser = userRepository.findByAuth0Id(auth0Id)
                .orElseThrow(() -> new IllegalStateException("User not found."));

        // Step 3: Use the custom query to fetch the user's characters
        List<Character> characters = characterRepository.findCharactersByUserId(currentUser.getId());

        // Step 4: Add characters to the model
        model.addAttribute("characters", characters);

        // Handle empty character list message
        if (characters.isEmpty()) {
            model.addAttribute("message", "You have no characters yet.");
        }

        return "user-characters"; // Return the Thymeleaf template name
    }

    // View Character Details
    @GetMapping("/character/{id}")
    public String viewCharacter(@PathVariable Long id, Model model) {
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found"));
        model.addAttribute("character", character);
        return "character-details"; // Thymeleaf template for displaying all fields
    }

    // Edit Character
    @GetMapping("/character/edit/{id}")
    public String editCharacter(@PathVariable Long id, Model model) {
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found"));
        model.addAttribute("character", character);
        return "character-creation"; // Return to the character creation/edit form
    }

    // Copy Character
    @GetMapping("/character/copy/{id}")
    public String copyCharacter(@PathVariable Long id) {
        Character original = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found"));
        Character copy = new Character();
        BeanUtils.copyProperties(original, copy, "id", "createdAt", "updatedAt");
        copy.setCharacterName(original.getCharacterName() + " (Copy)");
        characterRepository.save(copy);
        return "redirect:/all-characters";
    }

    // Delete Character
    @GetMapping("/character/delete/{id}")
    public String deleteCharacter(@PathVariable Long id) {
        characterRepository.deleteById(id);
        return "redirect:/all-characters";
    }

}





