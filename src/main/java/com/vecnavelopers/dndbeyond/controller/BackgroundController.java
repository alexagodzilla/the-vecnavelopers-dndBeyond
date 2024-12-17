package com.vecnavelopers.dndbeyond.controller;

import com.vecnavelopers.dndbeyond.model.Background;
import com.vecnavelopers.dndbeyond.service.BackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String getBackgroundByName(
            @PathVariable String backgroundName,
            @RequestParam(required = false) Long characterId,
            @RequestParam(required = false) Long userId,
            Model model) {
        String lowerCaseName = backgroundName.toLowerCase();
        Background background = backgroundService.getBackgroundByName(lowerCaseName);
        model.addAttribute("background", background);
        model.addAttribute("characterId", characterId);
        model.addAttribute("userId", userId);
        return "background-details";
    }
}
