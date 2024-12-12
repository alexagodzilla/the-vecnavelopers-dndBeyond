package com.vecnavelopers.dndbeyond.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    private Character character;

    @BeforeEach
    void setUp() {
        character = new Character(1L, "Test Character", "Description",
                "Warrior", "Barbarian", "Human", "Subspecies", "Origin", 1,
                10,  12,  14,  16,  18,  20,
                "Neutral",  100,  120,  18,  2, 1000,  50,
                 5, null, null);
    }


    @Test
    void testCharacterName() {
        character.setCharacterName("Updated Name");
        assertEquals("Updated Name", character.getCharacterName());
    }

    @Test
    void testCharacterDefaults() {
        assertEquals(Integer.valueOf(1), character.getCharacterLevel());
        assertEquals(Integer.valueOf(10), character.getCharacterStrength());
        assertEquals(Integer.valueOf(100), character.getCharacterCurrentHp());
    }

    @Test
    void testToString() {
        String expectedToString = "Character(id=null, userId=1, characterName=Test Character, " +
                "characterDescription=Description, characterClass=Warrior, characterSubclass=Barbarian, " +
                "characterSpecies=Human, characterSubspecies=Subspecies, characterOrigin=Origin, " +
                "characterLevel=1, characterStrength=10, characterDexterity=12, characterConstitution=14, " +
                "characterIntelligence=16, characterWisdom=18, characterCharisma=20, characterAlignment=Neutral, " +
                "characterCurrentHp=100, characterMaxHp=120, characterArmourClass=18, characterInitiative=2, " +
                "characterExp=1000, characterCoin=50, characterProficiencyBonus=5, createdAt=null, updatedAt=null)";

        assertEquals(expectedToString, character.toString());
    }
}
