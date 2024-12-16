package com.vecnavelopers.dndbeyond.service;

import com.vecnavelopers.dndbeyond.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {
    @Autowired
    private CharacterRepository characterRepository; // Inject the repository to interact with the DB

    public void deleteCharacter(Long id) {
        characterRepository.deleteById(id);
    }
}