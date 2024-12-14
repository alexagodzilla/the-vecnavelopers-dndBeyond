package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.dto.AbilityScoresDto;
import com.vecnavelopers.dndbeyond.model.Character;
import com.vecnavelopers.dndbeyond.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;


@RestController
public class AbilityScoresController {
    private final CharacterRepository characterRepository;

    @Autowired
    public AbilityScoresController(CharacterRepository characterRepository){
        this.characterRepository = characterRepository;
    }

    @PostMapping("/abilityScores/{id}")
    public boolean saveAbilityScores(AbilityScoresDto dto, @PathVariable Long id){
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found"));
        character.setCharacterStrength(dto.getStrength());
        character.setCharacterDexterity(dto.getDexterity());
        character.setCharacterConstitution(dto.getConstitution());
        character.setCharacterIntelligence(dto.getIntelligence());
        character.setCharacterWisdom(dto.getWisdom());
        character.setCharacterCharisma(dto.getCharisma());
        return false;
    }

}
