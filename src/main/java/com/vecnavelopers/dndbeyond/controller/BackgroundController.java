package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.model.Background;
import com.vecnavelopers.dndbeyond.service.BackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/background")
public class BackgroundController {

    public final BackgroundService backgroundService;

    @Autowired
    public BackgroundController(BackgroundService backgroundService) {
        this.backgroundService = backgroundService;
    }

    @GetMapping("/{backgroundName}")
    public String getBackgroundByName(@PathVariable String backgroundName, Model model) {
        String lowerCaseName = backgroundName.toLowerCase();
        Optional<Background> background = backgroundService.getBackgroundByName(lowerCaseName);
        model.addAttribute("background", background);
        return "background-details";
    }
}
