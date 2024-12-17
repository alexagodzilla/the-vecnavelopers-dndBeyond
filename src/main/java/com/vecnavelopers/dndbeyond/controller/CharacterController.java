package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.model.*;
import com.vecnavelopers.dndbeyond.repository.BackgroundRepository;
import com.vecnavelopers.dndbeyond.repository.UserRepository;
import com.vecnavelopers.dndbeyond.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private CharacterService characterService;

    @Autowired
    private SpeciesService speciesService;
    private final BackgroundService backgroundService;
    private final ClassService classService;
    private final CurrentUserService currentUserService;
    private final CharacterRepository characterRepository;



    public CharacterController(BackgroundService backgroundService, ClassService classService, CurrentUserService currentUserService, CharacterRepository characterRepository) {
        this.backgroundService = backgroundService;
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
        // Intellij believed new Character was char so I need to tell java what character I am importing by specifying is as below
        com.vecnavelopers.dndbeyond.model.Character newCharacter = new com.vecnavelopers.dndbeyond.model.Character();
        newCharacter.setUserId(userId);
        characterRepository.save(newCharacter);
        return "redirect:/choose-class/character/" + newCharacter.getId();
    }


    @GetMapping("/choose-class/character/{id}")
    public ModelAndView chooseClass(@PathVariable Long id) {
        ModelAndView classSelectionPage = new ModelAndView("class-selection");
        List<ClassSummary> classSummaryList = classService.getAllClasses();
        Long currentUserId = currentUserService.getCurrentUserId();
        classSelectionPage.addObject("classSummaryList", classSummaryList);
        classSelectionPage.addObject("userId", currentUserId);
        classSelectionPage.addObject("characterId", id);
        return classSelectionPage;
    }

    @DeleteMapping("/choose-class/character/{id}")
    public String deleteCharacter(@PathVariable Long id) {
        Long currentUserId = currentUserService.getCurrentUserId();
        characterService.deleteCharacter(id);
        return "redirect:/profile/" + currentUserId;
    }

    @PatchMapping("/update-class-name")
    public String updateClassName(@RequestParam String className, @RequestParam Long characterId) {
        characterService.updateCharacterClass(characterId, className);
        return "redirect:/choose-species/character/" + characterId;
    }

    @GetMapping("/choose-species/character/{id}")
    public ModelAndView chooseSpecies(@PathVariable Long id) {
        ModelAndView speciesSelectionPage = new ModelAndView("species-selection");
        List<SpeciesSummary> speciesSummaryList = speciesService.getAllSpecies();
        Long currentUserId = currentUserService.getCurrentUserId();
        speciesSelectionPage.addObject("speciesSummaryList", speciesSummaryList);
        speciesSelectionPage.addObject("userId", currentUserId);
        speciesSelectionPage.addObject("characterId", id);
        return speciesSelectionPage;
    }

    @PatchMapping("/update-species-name")
    public String updateSpeciesName(@RequestParam String speciesName, @RequestParam Long characterId) {
        characterService.updateCharacterSpecies(characterId, speciesName);
        return "redirect:/choose-background/character/" + characterId;
    }

    @GetMapping("/choose-background/character/{id}")
    public ModelAndView chooseBackground(@PathVariable Long id) {
        ModelAndView backgroundSelectionPage = new ModelAndView("background-selection");
        List<Background> backgroundList = backgroundService.getAllBackgrounds();
        Long currentUserId = currentUserService.getCurrentUserId();
        backgroundSelectionPage.addObject("backgroundList", backgroundList);
        backgroundSelectionPage.addObject("userId", currentUserId);
        backgroundSelectionPage.addObject("characterId", id);
        return backgroundSelectionPage;
    }

    @PatchMapping("/update-background-name")
    public String updateBackgroundName(@RequestParam String backgroundName, @RequestParam Long characterId) {
        characterService.updateCharacterBackground(characterId, backgroundName);
        return "redirect:/abilityScores/" + characterId;
    }
}
