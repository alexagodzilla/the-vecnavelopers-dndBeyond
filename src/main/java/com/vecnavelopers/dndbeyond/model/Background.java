package com.vecnavelopers.dndbeyond.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "backgrounds")
public class Background {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "background_name", nullable = false, unique = true)
    private String backgroundName;

    @Column(name = "background_description", nullable = false, unique = true)
    private String backgroundDescription;

    @Column(name = "background_skill_proficiency_1", nullable = false)
    private String backgroundSkillProficiency1;

    @Column(name = "background_skill_proficiency_2", nullable = false)
    private String backgroundSkillProficiency2;

    @Column(name = "background_ability_score_1", nullable = false)
    private String backgroundAbilityScore1;

    @Column(name = "background_ability_score_2", nullable = false)
    private String backgroundAbilityScore2;

    @Column(name = "background_ability_score_3", nullable = false)
    private String backgroundAbilityScore3;

    @Column(name = "background_tool_proficiency", nullable = false)
    private String backgroundToolProficiency;

    @Column(name = "background_feat", nullable = false)
    private String backgroundFeat;

    @Column(name = "background_image", nullable = false)
    private String backgroundImage;

    // Getters and Setters

    public String getBackgroundName() { return backgroundName; }

    public void setBackgroundName(String backgroundName) { this.backgroundName = backgroundName; }

    public String getBackgroundDescription() { return backgroundDescription; }

    public void setBackgroundDescription(String backgroundDescription) { this.backgroundDescription = backgroundDescription; }

    public String getBackgroundSkillProficiency1() { return backgroundSkillProficiency1; }

    public void setBackgroundSkillProficiency1(String backgroundSkillProficiency1) { this.backgroundSkillProficiency1 = backgroundSkillProficiency1; }

    public String getBackgroundSkillProficiency2() { return backgroundSkillProficiency2; }

    public void setBackgroundSkillProficiency2(String backgroundSkillProficiency2) { this.backgroundSkillProficiency2 = backgroundSkillProficiency2; }

    public String getBackgroundAbilityScore1() { return backgroundAbilityScore1; }

    public void setBackgroundAbilityScore1(String backgroundAbilityScore1) { this.backgroundAbilityScore1 = backgroundAbilityScore1; }

    public String getBackgroundAbilityScore2() { return backgroundAbilityScore2; }

    public void setBackgroundAbilityScore2(String backgroundAbilityScore2) { this.backgroundAbilityScore2 = backgroundAbilityScore2; }

    public String getBackgroundAbilityScore3() { return backgroundAbilityScore3; }

    public void setBackgroundAbilityScore3(String backgroundAbilityScore3) { this.backgroundAbilityScore3 = backgroundAbilityScore3; }

    public String getBackgroundToolProficiency() { return backgroundToolProficiency; }

    public void setBackgroundToolProficiency(String backgroundToolProficiency) { this.backgroundToolProficiency = backgroundToolProficiency; }

    public String getBackgroundFeat() { return backgroundFeat; }

    public void setBackgroundFeat(String backgroundFeat) { this.backgroundFeat = backgroundFeat; }

    public String getBackgroundImage() { return backgroundImage; }

    public void setBackgroundImage(String backgroundImage) { this.backgroundImage = backgroundImage; }
}
