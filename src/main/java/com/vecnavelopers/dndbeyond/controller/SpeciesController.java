package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.model.SpeciesDetails;
import com.vecnavelopers.dndbeyond.service.SpeciesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SpeciesController {

    private final SpeciesService speciesService;

    public SpeciesController(SpeciesService speciesService) { this.speciesService = speciesService; }

    @GetMapping("/species/{speciesName}")
    public String getSpeciesDetails(@PathVariable String speciesName, Model model) {
        SpeciesDetails speciesDetails = speciesService.getSpeciesDetails(speciesName);
        model.addAttribute("speciesDetails", speciesDetails);
        return "species-details";
    }
}
