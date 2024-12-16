package com.vecnavelopers.dndbeyond.service;

import com.vecnavelopers.dndbeyond.model.Background;
import com.vecnavelopers.dndbeyond.repository.BackgroundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BackgroundService {

    private final BackgroundRepository backgroundRepository;

    @Autowired
    public BackgroundService(BackgroundRepository backgroundRepository) {
        this.backgroundRepository = backgroundRepository;
    }

    // Fetch all backgrounds
    public List<Background> getAllBackgrounds() {
        return backgroundRepository.findAll();
    }

    // Fetch a background by name
    public Optional<Background> getBackgroundByName(String backgroundName) {
        return backgroundRepository.findByBackgroundNameIgnoreCase(backgroundName);
    }
}
