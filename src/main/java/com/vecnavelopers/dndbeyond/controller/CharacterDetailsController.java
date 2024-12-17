package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.dto.CharacterDetailsDto;
import com.vecnavelopers.dndbeyond.model.Character;
import com.vecnavelopers.dndbeyond.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CharacterDetailsController {
    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterDetailsController(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @GetMapping("/characterDetails/{id}")
    public ModelAndView getCharacterDetails(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView( "character-details");
        modelAndView.addObject("characterId", id);
        return modelAndView;

    }

    @PostMapping("/characterDetails/{id}")
    public void saveCharacterDetails(@RequestBody CharacterDetailsDto dto, @PathVariable Long id){
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found"));
        character.setCharacterName(dto.getName());
        character.setCharacterPicUrl(dto.getPicUrl());
        character.setCharacterDescription(dto.getDescriprion());
        characterRepository.save(character);
    }
}
