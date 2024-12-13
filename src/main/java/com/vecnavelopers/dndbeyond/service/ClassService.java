package com.vecnavelopers.dndbeyond.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClassService {
    public Map<String, String> getClassDescriptions() {
        Map<String, String> classDescriptions = new HashMap<>();
        classDescriptions.put("Warrior", "A fierce fighter skilled in combat.");
        classDescriptions.put("Mage", "A master of magical spells and arcane arts.");
        classDescriptions.put("Rogue", "A stealthy and agile character who excels in trickery.");
        classDescriptions.put("Cleric", "A holy character who supports allies with healing powers.");
        return classDescriptions;
    }
}