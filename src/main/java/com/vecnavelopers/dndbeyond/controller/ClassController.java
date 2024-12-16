package com.vecnavelopers.dndbeyond.controller;


import com.vecnavelopers.dndbeyond.model.ClassDetails;
import com.vecnavelopers.dndbeyond.service.ClassService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

//    @GetMapping("/classes/{className}")
//    public String getClassDetails(@PathVariable String className, Model model) {
//        ClassDetails classDetails = classService.getClassDetails(className);
//        model.addAttribute("classDetails", classDetails);
//        return "class-details";
//    }

    @GetMapping("/classes/{className}")
    public String getClassDetails(
            @PathVariable String className,
            @RequestParam(required = false) Long characterId,
            @RequestParam(required = false) Long userId,
            Model model) {
        ClassDetails classDetails = classService.getClassDetails(className);
        model.addAttribute("classDetails", classDetails);
        model.addAttribute("characterId", characterId);
        model.addAttribute("userId", userId);
        return "class-details";
    }



}