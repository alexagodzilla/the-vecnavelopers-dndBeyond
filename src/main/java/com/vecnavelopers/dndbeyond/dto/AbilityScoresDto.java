package com.vecnavelopers.dndbeyond.dto;

import java.util.Map;

public class AbilityScoresDto {
    private final Integer strength;
    private final Integer dexterity;
    private final Integer constitution;
    private final Integer intelligence;
    private final Integer wisdom;
    private final Integer charisma;

    public AbilityScoresDto(Integer strength, Integer dexterity, Integer constitution, Integer intelligence, Integer wisdom, Integer charisma) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }

    public Integer getStrength() {
        return strength;
    }

    public Integer getDexterity() {
        return dexterity;
    }

    public Integer getConstitution() {
        return constitution;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public Integer getWisdom() {
        return wisdom;
    }

    public Integer getCharisma() {
        return charisma;
    }
}
