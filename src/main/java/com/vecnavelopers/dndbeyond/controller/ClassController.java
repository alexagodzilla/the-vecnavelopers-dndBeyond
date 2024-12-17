package com.vecnavelopers.dndbeyond.controller;


import com.vecnavelopers.dndbeyond.model.ClassDetails;
import com.vecnavelopers.dndbeyond.model.ClassExtraDetails;
import com.vecnavelopers.dndbeyond.model.Spell;
import com.vecnavelopers.dndbeyond.repository.ClassExtraDetailsRepository;
import com.vecnavelopers.dndbeyond.service.ClassService;

import com.vecnavelopers.dndbeyond.service.SpellService;
import com.vecnavelopers.dndbeyond.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/classes")
public class ClassController {

    private final ClassService classService;
    private final ClassExtraDetailsRepository classExtraDetailsRepository;
    private final SpellService spellService;

    public ClassController(ClassService classService, ClassExtraDetailsRepository classExtraDetailsRepository, SpellService spellService) {
        this.classService = classService;
        this.classExtraDetailsRepository = classExtraDetailsRepository;
        this.spellService = spellService;
    }


    @GetMapping("/{className}")
    public String getClassDetails(
            @PathVariable String className,
            @RequestParam(required = false) Long characterId,
            @RequestParam(required = false) Long userId,
            Model model) throws IOException {
        ClassDetails classDetails = classService.getClassDetails(className);
        model.addAttribute("classDetails", classDetails);
        ClassExtraDetails extraDetails = classExtraDetailsRepository.findByClassName(className);
        model.addAttribute("extraDetails", extraDetails);
        // Get the list of spells for the class
        List<Spell> classSpells = spellService.getSpellsForClass(className);

        // Separate level 0 (Cantrips) and level 1 spells
        List<Spell> cantrips = classSpells.stream()
                .filter(spell -> spell.getLevel() == 0)
                .collect(Collectors.toList());

        List<Spell> level1Spells = classSpells.stream()
                .filter(spell -> spell.getLevel() == 1)
                .collect(Collectors.toList());

        model.addAttribute("cantrips", cantrips);
        model.addAttribute("level1Spells", level1Spells);
        model.addAttribute("characterId", characterId);
        model.addAttribute("userId", userId);
        return "class-details";
    }

    @ModelAttribute("stringUtils")
    public StringUtils stringUtils() {
        return new StringUtils();
    }



}