package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.dto.AbilityScoresDto;
import com.vecnavelopers.dndbeyond.dto.NameAndPicDto;
import com.vecnavelopers.dndbeyond.model.Character;
import com.vecnavelopers.dndbeyond.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class CharacterNameController {
    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterNameController(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @PostMapping("/nameAndPic/{id}")
    public void saveNameAndPic(@RequestBody NameAndPicDto dto, @PathVariable Long id){
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found"));
        character.setCharacterName(dto.getName());
        character.setCharacterPicUrl(dto.getPicUrl());
        characterRepository.save(character);
    }
}
