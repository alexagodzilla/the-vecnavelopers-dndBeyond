package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.model.ClassDetails;
import com.vecnavelopers.dndbeyond.service.DndApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CharacterController {

    private final DndApiService dndApiService;

    public CharacterController(DndApiService dndApiService) {
        this.dndApiService = dndApiService;
    }

    @GetMapping("/class-details")
    public String getClassDetails(@RequestParam String className, Model model) {
        ClassDetails classDetails = dndApiService.getClassDetails(className.toLowerCase());
        model.addAttribute("classDetails", classDetails);
        return "class-details"; // returns the name of the Thymeleaf template
    }
}
