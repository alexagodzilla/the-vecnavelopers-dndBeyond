package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.model.ClassDetails;
import com.vecnavelopers.dndbeyond.service.ClassService;
import com.vecnavelopers.dndbeyond.service.ClassService;
import com.vecnavelopers.dndbeyond.service.DndApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
public class CharacterController {

    private final DndApiService dndApiService;
    private final ClassService classService;

    public CharacterController(DndApiService dndApiService, ClassService classService) {
        this.dndApiService = dndApiService;
        this.classService = classService;
    }

    @GetMapping("/classes/{className}")
    public String getClassDetails(@PathVariable String className, Model model) {
        ClassDetails classDetails = dndApiService.getClassDetails(className);
        model.addAttribute("classDetails", classDetails);
        return "class-details"; // returns the name of the Thymeleaf template
    }

    @GetMapping("/class_selection")
    public String showClassSelectionPage(Model model) {
//        List<String> characterClass = List.of("Warrior", "Mage", "Rogue", "Cleric");
        Map<String, String> classDescription = classService.getClassDescriptions();
        model.addAttribute("classDescription", classDescription);
//        model.addAttribute("characterClass", characterClass);
        return "class-selection";
    }

//    @GetMapping("/class_selection")
//    public String showClassSelectionPage(Model model) {
//        List<String> characterClassNames = List.of("Warrior", "Mage", "Rogue", "Cleric");
//
//        // Create a list to store details of all classes
//        List<ClassDetails> classDetailsList = new ArrayList<>();
//
//        // Fetch class details for each class name
//        for (String className : characterClassNames) {
//            ClassDetails classDetails = dndApiService.getClassDetails(className);
//            classDetailsList.add(classDetails);
//        }
//
//        // Add the class details list to the model
//        model.addAttribute("classDetailsList", classDetailsList);
//
//        return "class-selection";
//    }



}
