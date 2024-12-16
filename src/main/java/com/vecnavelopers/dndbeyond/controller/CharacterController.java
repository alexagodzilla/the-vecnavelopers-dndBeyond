package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.model.ClassDetails;
import com.vecnavelopers.dndbeyond.model.User;
import com.vecnavelopers.dndbeyond.repository.UserRepository;
import com.vecnavelopers.dndbeyond.service.ClassService;

import com.vecnavelopers.dndbeyond.service.CurrentUserService;
import com.vecnavelopers.dndbeyond.service.DndApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import com.vecnavelopers.dndbeyond.repository.CharacterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


@Controller
public class CharacterController {

    private final DndApiService dndApiService;
    private final ClassService classService;
    private final CurrentUserService currentUserService;
    private final CharacterRepository characterRepository;

    public CharacterController(DndApiService dndApiService, ClassService classService, CurrentUserService currentUserService, CharacterRepository characterRepository) {
        this.dndApiService = dndApiService;
        this.classService = classService;
        this.currentUserService = currentUserService;
        this.characterRepository = characterRepository;
    }


    @GetMapping("/create-character")
    public ModelAndView goToCreateCharacterPage() {
        ModelAndView createCharacterPage = new ModelAndView("create-character");
        try {
            Long currentUserId = currentUserService.getCurrentUserId();
            createCharacterPage.addObject("userId", currentUserId);
            return createCharacterPage;
        } catch (IllegalStateException e) {
            createCharacterPage.setViewName("redirect:/login");
            return createCharacterPage;
        }
    }


    @PostMapping("/create-character")
    public String createCharacter() {
        Long userId = currentUserService.getCurrentUserId();
        // Intellij belived new Character was char so I need to tell java what character I am importing by sepcifying is as below
        com.vecnavelopers.dndbeyond.model.Character newCharacter = new com.vecnavelopers.dndbeyond.model.Character();
        newCharacter.setUserId(userId);
        characterRepository.save(newCharacter);
        return "redirect:/choose-class/character/" + newCharacter.getId();
    }



    @GetMapping("/choose-class/character/{id}")
    public ModelAndView chooseClass(@PathVariable Long id) {
        ModelAndView classSelectionPage = new ModelAndView("class-selection");
        List<ClassDetails> classDetailsList = classService.getClassDescriptions();
        Long currentUserId = currentUserService.getCurrentUserId();
        classSelectionPage.addObject("characterId", id);
        classSelectionPage.addObject("userId", currentUserId);
        classSelectionPage.addObject("classDetails", classDetailsList);
        return classSelectionPage;
    }

    @PostMapping("/delete-character/{characterId}")
    public String deleteCharacter(@PathVariable Long characterId, @RequestParam Long userId) {
        System.out.println("Deleting character with ID: " + characterId + " for user: " + userId);
        characterRepository.deleteById(characterId);
        Long currentUserId = currentUserService.getCurrentUserId();
        return "redirect:/profile/" + currentUserId;
    }



}
