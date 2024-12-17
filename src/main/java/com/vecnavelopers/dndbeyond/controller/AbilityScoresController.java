package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.dto.AbilityScoresDto;
import com.vecnavelopers.dndbeyond.model.Character;
import com.vecnavelopers.dndbeyond.model.ClassDetails;
import com.vecnavelopers.dndbeyond.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
public class AbilityScoresController {
    private final CharacterRepository characterRepository;

    @Autowired
    public AbilityScoresController(CharacterRepository characterRepository){
        this.characterRepository = characterRepository;
    }

    @GetMapping("/abilityScores/{id}")
    public ModelAndView getClassDetails(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView( "ability-scores");
        modelAndView.addObject("abilities",
                List.of("strength", "dexterity", "constitution", "intelligence", "wisdom", "charisma"));
        modelAndView.addObject("characterId", id);
        return modelAndView;
    }

    @PatchMapping("/abilityScores/{id}")
    public void saveAbilityScores(@RequestBody AbilityScoresDto dto, @PathVariable Long id){
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found"));
        character.setCharacterStrength(dto.getStrength());
        character.setCharacterDexterity(dto.getDexterity());
        character.setCharacterConstitution(dto.getConstitution());
        character.setCharacterIntelligence(dto.getIntelligence());
        character.setCharacterWisdom(dto.getWisdom());
        character.setCharacterCharisma(dto.getCharisma());

        characterRepository.save(character);
    }

}
