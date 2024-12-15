package com.vecnavelopers.dndbeyond.service;

import com.vecnavelopers.dndbeyond.model.User;
import com.vecnavelopers.dndbeyond.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    private final UserRepository userRepository;

    public CurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long getCurrentUserId() {
        // Get the principal (logged-in user info)
        DefaultOidcUser principal = (DefaultOidcUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        // Retrieve the email from OIDC attributes
        String email = (String) principal.getAttributes().get("email");

        // Find the user by email and return the ID
        return userRepository.findUserByUsername(email)
                .map(User::getId)
                .orElse(null); // Return null if the user is not found
    }
}