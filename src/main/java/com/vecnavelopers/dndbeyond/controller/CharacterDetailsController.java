package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.dto.CharacterDetailsDto;
import com.vecnavelopers.dndbeyond.model.Character;
import com.vecnavelopers.dndbeyond.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@RestController
public class CharacterDetailsController {
    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterDetailsController(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @GetMapping("/characterDetails/{id}")
    public ModelAndView getCharacterDetails(@PathVariable Long id) {
        Character character = characterRepository.findById(id)
                .orElse(new Character());
        ModelAndView modelAndView = new ModelAndView("character-details");
        modelAndView.addObject("characterId", id);
        modelAndView.addObject("characterName", character.getCharacterName());
        modelAndView.addObject("characterPicUrl", character.getCharacterPicUrl());
        modelAndView.addObject("characterDescription", character.getCharacterDescription());
        return modelAndView;

    }

//    @PatchMapping("/characterDetails/{id}")
//    public ResponseEntity saveCharacterDetails(@RequestBody CharacterDetailsDto dto, @PathVariable Long id){
//        Character character = characterRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Character not found"));
//        character.setCharacterName(dto.getName());
//        character.setCharacterPicUrl(dto.getPicUrl());
//        character.setCharacterDescription(dto.getDescriprion());
//        characterRepository.save(character);
//        Map<String, String> response = new HashMap<>();
//        response.put("redirectUrl", "/all-characters");
//        return ResponseEntity.ok(response);
//    }

    @PatchMapping("/characterDetails/{id}")
    public ResponseEntity saveCharacterDetails(@RequestParam("characterName") String characterName,
                                               @RequestParam("characterDescription") String characterDescription,
                                               @RequestParam(value = "characterPicUrl", required = false) MultipartFile characterPicUrl,
                                               @PathVariable Long id) {
        try {
            // Find the character by ID
            Character character = characterRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Character not found"));

            // Update character name and description
            character.setCharacterName(characterName);
            character.setCharacterDescription(characterDescription);

            // Handle character picture upload if a file is provided
            if (characterPicUrl != null && !characterPicUrl.isEmpty()) {
                String fileName = characterPicUrl.getOriginalFilename();
                Path uploadPath = Paths.get("src/main/resources/static/uploads").toAbsolutePath();
                Files.createDirectories(uploadPath); // Ensure the directory exists
                Files.copy(characterPicUrl.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                character.setCharacterPicUrl(fileName); // Store the URL or file path
            }

            // Save character details to the database
            characterRepository.save(character);

            // Return response with redirect URL
            Map<String, String> response = new HashMap<>();
            response.put("redirectUrl", "/all-characters");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> response = new HashMap<>();
            response.put("message", "An error occurred while saving character details.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

