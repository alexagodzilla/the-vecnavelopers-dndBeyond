package com.vecnavelopers.dndbeyond.controller;

import ch.qos.logback.core.model.Model;
import com.vecnavelopers.dndbeyond.model.User;
import com.vecnavelopers.dndbeyond.repository.UserRepository;
import com.vecnavelopers.dndbeyond.service.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private CurrentUserService currentUserService;

    @GetMapping("/users/after-login")
    public RedirectView afterLogin() {
        Long userId = currentUserService.getCurrentUserId();
        String username = (String) principal.getAttributes().get("email");
        userRepository
                .findUserByUsername(username)
                .orElseGet(() -> userRepository.save(new User(username)));

        return new RedirectView("/profile/setup");
    }

        if (userId != null) {
            return new RedirectView("/profile/" + userId);
        } else {
            // I will figure out a more intelligent way to handle the error later
            return new RedirectView("/error");
        }
    }

}
