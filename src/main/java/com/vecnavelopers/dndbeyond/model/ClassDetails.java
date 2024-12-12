package com.vecnavelopers.dndbeyond.model;

import com.fasterxml.jackson.databind.JsonNode;

public class ClassDetails {
    private String className;
    private int hitDie;
    private JsonNode classLevels; // Can be a List or Map, depending on your needs
    private JsonNode spellcasting;
    private String spellcastingAbility;
    private JsonNode spells; // A list of spells
    private JsonNode proficiencyChoices;
    private JsonNode proficiencies;
    private JsonNode savingThrows;
    private JsonNode subclasses;

    // Getters and setters
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getHitDie() {
        return hitDie;
    }

    public void setHitDie(int hitDie) {
        this.hitDie = hitDie;
    }

    public JsonNode getClassLevels() {
        return classLevels;
    }

    public void setClassLevels(JsonNode classLevels) {
        this.classLevels = classLevels;
    }

    public JsonNode getSpellcasting() {
        return spellcasting;
    }

    public void setSpellcasting(JsonNode spellcasting) {
        this.spellcasting = spellcasting;
    }

    public String getSpellcastingAbility() {
        return spellcastingAbility;
    }

    public void setSpellcastingAbility(String spellcastingAbility) {
        this.spellcastingAbility = spellcastingAbility;
    }

    public JsonNode getSpells() {
        return spells;
    }

    public void setSpells(JsonNode spells) {
        this.spells = spells;
    }

    public JsonNode getProficiencyChoices() {
        return proficiencyChoices;
    }

    public void setProficiencyChoices(JsonNode proficiencyChoices) {
        this.proficiencyChoices = proficiencyChoices;
    }

    public JsonNode getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(JsonNode proficiencies) {
        this.proficiencies = proficiencies;
    }

    public JsonNode getSavingThrows() {
        return savingThrows;
    }

    public void setSavingThrows(JsonNode savingThrows) {
        this.savingThrows = savingThrows;
    }

    public JsonNode getSubclasses() {
        return subclasses;
    }

    public void setSubclasses(JsonNode subclasses) {
        this.subclasses = subclasses;
    }
}
