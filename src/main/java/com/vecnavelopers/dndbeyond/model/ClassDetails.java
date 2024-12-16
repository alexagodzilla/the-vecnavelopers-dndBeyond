package com.vecnavelopers.dndbeyond.model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class ClassDetails {
    private String className;
    private String description;
    private String imageUrl;
    private int hitDie;
    private JsonNode classLevels; // Can be a List or Map, depending on your needs
    private Spellcasting spellcasting;
    private String spellcastingAbility;
    private JsonNode spells; // A list of spells
    private JsonNode proficiencyChoices;
    private List<Proficiency> proficiencies;
    private List<Proficiency> savingThrows;
    private List<StartingEquipment> startingEquipment;
    private JsonNode subclasses;

    // Getters and setters

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

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

    public void setSpellcasting(Spellcasting spellcasting) {
        this.spellcasting = spellcasting;
    }

    public Spellcasting getSpellcasting() {
        return spellcasting;
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

    public List<Proficiency> getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(List<Proficiency> proficiencies) {
        this.proficiencies = proficiencies;
    }

    public List<Proficiency> getSavingThrows() {
        return savingThrows;
    }

    public void setSavingThrows(List<Proficiency> savingThrows) {
        this.savingThrows = savingThrows;
    }

    public List<StartingEquipment> getStartingEquipment() { return startingEquipment; }

    public void setStartingEquipment(List<StartingEquipment> startingEquipment) { this.startingEquipment = startingEquipment; }

    public JsonNode getSubclasses() {
        return subclasses;
    }

    public void setSubclasses(JsonNode subclasses) {
        this.subclasses = subclasses;
    }
}
