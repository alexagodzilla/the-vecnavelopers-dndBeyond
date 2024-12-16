package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.model.User;
import com.vecnavelopers.dndbeyond.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile/{userId}")
    public ModelAndView viewProfile(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ModelAndView profilePage = new ModelAndView("profile-page");
        profilePage.addObject("user", user);

        return profilePage;
    }


}

