package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.model.SpeciesDetails;
import com.vecnavelopers.dndbeyond.model.SpeciesExtraDetails;
import com.vecnavelopers.dndbeyond.repository.SpeciesExtraDetailsRepository;
import com.vecnavelopers.dndbeyond.service.SpeciesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/species")
public class SpeciesController {

    private final SpeciesService speciesService;
    private final SpeciesExtraDetailsRepository speciesExtraDetailsRepository;

    public SpeciesController(SpeciesService speciesService, SpeciesExtraDetailsRepository speciesExtraDetailsRepository) { this.speciesService = speciesService;
        this.speciesExtraDetailsRepository = speciesExtraDetailsRepository;
    }

    @GetMapping("/{speciesName}")
    public String getSpeciesDetails(@PathVariable String speciesName, Model model) {
        SpeciesDetails speciesDetails = speciesService.getSpeciesDetails(speciesName);
        model.addAttribute("speciesDetails", speciesDetails);
        SpeciesExtraDetails extraDetails = speciesExtraDetailsRepository.findBySpeciesName(speciesName);
        model.addAttribute("extraDetails", extraDetails);
        return "species-details";
    }
}
