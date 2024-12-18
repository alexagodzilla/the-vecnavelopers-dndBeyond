package com.vecnavelopers.dndbeyond.service;

import com.vecnavelopers.dndbeyond.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {
    @Autowired
    private CharacterRepository characterRepository; // Inject the repository to interact with the DB

    public void deleteCharacter(Long id) {
        characterRepository.deleteById(id);
    }

    public void updateCharacterClassAndProficiencies(Long characterId, String className, List<String> proficiencies) {
        // Retrieve the Character or throw an exception if not found
        com.vecnavelopers.dndbeyond.model.Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new RuntimeException("Character not found"));

        // Update Class Name
        character.setCharacterClass(className);

        // Update Proficiencies (assumes that exactly 2 are provided)
        character.setCharacterProficiency1(proficiencies.get(0));
        character.setCharacterProficiency2(proficiencies.get(1));

        // Save the updated character information
        characterRepository.save(character);
    }

    public void updateCharacterSpecies(Long characterId, String speciesName) {
        com.vecnavelopers.dndbeyond.model.Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new RuntimeException("Character not found"));
        character.setCharacterSpecies(speciesName);
        characterRepository.save(character);
    }

//    public void removeCharacterSpecies(Long characterId) {
//        com.vecnavelopers.dndbeyond.model.Character character = characterRepository.findById(characterId)
//                .orElseThrow(() -> new RuntimeException("Character not found"));
//        character.setCharacterSpecies(null); // Assuming species name is stored in this field
//        characterRepository.save(character);
//    }

    public void updateCharacterBackground(Long characterId, String backgroundName) {
        com.vecnavelopers.dndbeyond.model.Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new RuntimeException("Character not found"));
        character.setCharacterOrigin(backgroundName);
        characterRepository.save(character);
    }
}