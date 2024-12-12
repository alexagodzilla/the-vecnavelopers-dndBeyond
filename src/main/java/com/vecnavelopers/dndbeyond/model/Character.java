package com.vecnavelopers.dndbeyond.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "character_name", length = 50)
    private String characterName;

    @Column(name = "character_description", length = 255)
    private String characterDescription;

    @Column(name = "character_class", length = 50)
    private String characterClass;

    @Column(name = "character_subclass", length = 50)
    private String characterSubclass;

    @Column(name = "character_species", length = 50)
    private String characterSpecies;

    @Column(name = "character_subspecies", length = 50)
    private String characterSubspecies;

    @Column(name = "character_origin", length = 50)
    private String characterOrigin;

    @Column(name = "character_level", nullable = false, columnDefinition = "SMALLINT default 1")
    private Integer characterLevel;

    @Column(name = "character_strength", nullable = false, columnDefinition = "SMALLINT default 8")
    private Integer characterStrength;

    @Column(name = "character_dexterity", nullable = false, columnDefinition = "SMALLINT default 8")
    private Integer characterDexterity;

    @Column(name = "character_constitution", nullable = false, columnDefinition = "SMALLINT default 8")
    private Integer characterConstitution;

    @Column(name = "character_intelligence", nullable = false, columnDefinition = "SMALLINT default 8")
    private Integer characterIntelligence;

    @Column(name = "character_wisdom", nullable = false, columnDefinition = "SMALLINT default 8")
    private Integer characterWisdom;

    @Column(name = "character_charisma", nullable = false, columnDefinition = "SMALLINT default 8")
    private Integer characterCharisma;

    @Column(name = "character_alignment", length = 50)
    private String characterAlignment;

    @Column(name = "character_current_hp", nullable = false, columnDefinition = "SMALLINT default 0")
    private Integer characterCurrentHp;

    @Column(name = "character_max_hp", nullable = false, columnDefinition = "SMALLINT default 0")
    private Integer characterMaxHp;

    @Column(name = "character_armour_class", nullable = false, columnDefinition = "SMALLINT default 0")
    private Integer characterArmourClass;

    @Column(name = "character_initiative", nullable = false, columnDefinition = "SMALLINT default 0")
    private Integer characterInitiative;

    @Column(name = "character_exp", nullable = false, columnDefinition = "INTEGER default 0")
    private Integer characterExp;

    @Column(name = "character_coin", nullable = false, columnDefinition = "SMALLINT default 0")
    private Integer characterCoin;

    @Column(name = "character_proficiency_bonus", nullable = false, columnDefinition = "SMALLINT default 0")
    private Integer characterProficiencyBonus;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;



    public Character() {
        this.characterLevel = 1;
        this.characterStrength = 8;
        this.characterDexterity = 8;
        this.characterConstitution = 8;
        this.characterIntelligence = 8;
        this.characterWisdom = 8;
        this.characterCharisma = 8;
        this.characterCurrentHp = 0;
        this.characterMaxHp = 0;
        this.characterArmourClass = 0;
        this.characterInitiative = 0;
        this.characterExp = 0;
        this.characterCoin = 0;
        this.characterProficiencyBonus = 0;
    }

    public Character(Long userId, String characterName, String characterDescription,
                     String characterClass, String characterSubclass, String characterSpecies,
                     String characterSubspecies, String characterOrigin, Integer characterLevel,
                     Integer characterStrength, Integer characterDexterity, Integer characterConstitution,
                     Integer characterIntelligence, Integer characterWisdom, Integer characterCharisma,
                     String characterAlignment, Integer characterCurrentHp, Integer characterMaxHp,
                     Integer characterArmourClass, Integer characterInitiative, Integer characterExp,
                     Integer characterCoin, Integer characterProficiencyBonus, Timestamp createdAt,
                     Timestamp updatedAt) {
        this.userId = userId;
        this.characterName = characterName;
        this.characterDescription = characterDescription;
        this.characterClass = characterClass;
        this.characterSubclass = characterSubclass;
        this.characterSpecies = characterSpecies;
        this.characterSubspecies = characterSubspecies;
        this.characterOrigin = characterOrigin;
        this.characterLevel = characterLevel;
        this.characterStrength = characterStrength;
        this.characterDexterity = characterDexterity;
        this.characterConstitution = characterConstitution;
        this.characterIntelligence = characterIntelligence;
        this.characterWisdom = characterWisdom;
        this.characterCharisma = characterCharisma;
        this.characterAlignment = characterAlignment;
        this.characterCurrentHp = characterCurrentHp;
        this.characterMaxHp = characterMaxHp;
        this.characterArmourClass = characterArmourClass;
        this.characterInitiative = characterInitiative;
        this.characterExp = characterExp;
        this.characterCoin = characterCoin;
        this.characterProficiencyBonus = characterProficiencyBonus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


}
