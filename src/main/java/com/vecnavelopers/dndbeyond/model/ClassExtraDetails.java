package com.vecnavelopers.dndbeyond.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "class_extra_details")
public class ClassExtraDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_index", nullable = false, unique = true)
    private Long classIndex;

    @Column(name = "class_name", nullable = false, unique = true)
    private String className;

    @Column(name = "class_tagline", nullable = false, unique = true)
    private String classTagline;

    @Column(name = "class_flavour", nullable = false, unique = true)
    private String classFlavour;

    @Column(name = "class_description", nullable = false, unique = true)
    private String classDescription;

    @Column(name = "class_primary_ability", nullable = false, unique = true)
    private String classPrimaryAbility;

    @Column(name = "class_icon")
    private String classIcon;

    // Getters and Setters

}
