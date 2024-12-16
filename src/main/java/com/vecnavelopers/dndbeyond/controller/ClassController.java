package com.vecnavelopers.dndbeyond.controller;


import com.vecnavelopers.dndbeyond.model.ClassDetails;
import com.vecnavelopers.dndbeyond.model.ClassExtraDetails;
import com.vecnavelopers.dndbeyond.repository.ClassExtraDetailsRepository;
import com.vecnavelopers.dndbeyond.service.ClassService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ClassController {

    private final ClassService classService;
    private final ClassExtraDetailsRepository classExtraDetailsRepository;

    public ClassController(ClassService classService, ClassExtraDetailsRepository classExtraDetailsRepository) {
        this.classService = classService;
        this.classExtraDetailsRepository = classExtraDetailsRepository;
    }

    @GetMapping("/classes/{className}")
    public String getClassDetails(@PathVariable String className, Model model) {
        ClassDetails classDetails = classService.getClassDetails(className);
        model.addAttribute("classDetails", classDetails);
        ClassExtraDetails extraDetails = classExtraDetailsRepository.findByClassName(className);
        model.addAttribute("extraDetails", extraDetails);
        return "class-details";
    }
}