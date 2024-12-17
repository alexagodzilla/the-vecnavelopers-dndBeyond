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

import java.util.jar.JarEntry;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private CurrentUserService currentUserService;

    @GetMapping("/users/after-login")
    public RedirectView afterLogin() {
        DefaultOidcUser principal = (DefaultOidcUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        String email = principal.getAttribute("email");
        if (email == null) {
            return new RedirectView("/error");
        }

        User user = userRepository.findUserByUsername(email)
                .orElseGet(() -> {
                    User newUser = new User(email);
                    return userRepository.save(newUser);
                });

        Long userId = user.getId();

        if (userId != null) {
            return new RedirectView("/profile/" + userId);
        } else {
            return new RedirectView("/error");
        }
    }
}