package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.model.ClassDetails;
import com.vecnavelopers.dndbeyond.model.User;
import com.vecnavelopers.dndbeyond.repository.UserRepository;
import com.vecnavelopers.dndbeyond.service.ClassService;

import com.vecnavelopers.dndbeyond.service.DndApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@Controller
public class CharacterController {

    @Autowired
    UserRepository userRepository;

    private final DndApiService dndApiService;
    private final ClassService classService;

    public CharacterController(DndApiService dndApiService, ClassService classService) {
        this.dndApiService = dndApiService;
        this.classService = classService;
    }


    @GetMapping("/create-character")
    public String showCreateCharacterPage(Model model) {

        return "create-character";
    }

    @GetMapping("/class_selection")
    public String showClassSelectionPage(Model model) {
        Map<String, String> classDescription = classService.getClassDescriptions();
        model.addAttribute("classDescription", classDescription);
        return "class-selection";
    }





}
