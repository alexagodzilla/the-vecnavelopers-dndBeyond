package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.model.User;
import com.vecnavelopers.dndbeyond.repository.UserRepository;
import com.vecnavelopers.dndbeyond.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    AuthService authService;

    private static final String UPLOAD_DIR = "uploads/";

    @GetMapping("/profile/setup")
    public String showProfileSetupPage() {
        return "profile-setup";
    }

    @PostMapping("/profile/setup")
    public String saveProfile(@RequestParam("displayName") String displayName,
                              @RequestParam("bio") String bio,
                              @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture,
                              Model model) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String auth0Id = null;
            if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
                auth0Id = jwt.getClaim("sub");
            }

            if (auth0Id == null) {
                throw new IllegalStateException("Authenticated user does not have an auth0_id.");
            }

            System.out.println("Auth0 ID: " + auth0Id);
            System.out.println("Display Name: " + displayName);
            System.out.println("Bio: " + bio);

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

            return "redirect:/profile/success"; // Redirect to the success page
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
}
