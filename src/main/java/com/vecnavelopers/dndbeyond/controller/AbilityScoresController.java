package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.dto.AbilityScoresDto;
import com.vecnavelopers.dndbeyond.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class AbilityScoresController {
  private final CharacterRepository characterRepository;

  @Autowired
  public AbilityScoresController(CharacterRepository characterRepository){
      this.characterRepository = characterRepository;
  }

   @PostMapping("/abilityScores")
    public boolean saveAbilityScores(AbilityScoresDto dto){
      return false;
   }

}
