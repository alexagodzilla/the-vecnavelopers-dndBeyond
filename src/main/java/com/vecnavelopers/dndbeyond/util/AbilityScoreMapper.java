package com.vecnavelopers.dndbeyond.util;

import java.util.Map;


// This utility class can be used to get the full names of the abilities if only the shorthand is available.
public class AbilityScoreMapper {

    private static final Map<String, String> abilityScoreMap = Map.of(
            "STR", "Strength",
            "DEX", "Dexterity",
            "CON", "Constitution",
            "INT", "Intelligence",
            "WIS", "Wisdom",
            "CHA", "Charisma"
    );

    public static String getFullName(String shorthand) {
        return abilityScoreMap.getOrDefault(shorthand, shorthand); // Default to shorthand if not found
    }
}

// Here is an example of how to implement the method:
//
//public class ClassService {
//
//    public void printAbility(String shorthand) {
//        String fullName = AbilityScoreMapper.getFullName(shorthand);
//        System.out.println("Full ability name: " + fullName);
//    }
//}