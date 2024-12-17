package com.vecnavelopers.dndbeyond.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "species_extra_details")
public class SpeciesExtraDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "species_index", nullable = false, unique = true)
    private String speciesIndex;

    @Column(name = "species_name", nullable = false, unique = true)
    private String speciesName;

    @Column(name = "species_tagline", nullable = false, unique = true)
    private String speciesTagline;

    @Column(name = "species_flavour", nullable = false, unique = true)
    private String speciesFlavour;

    @Column(name = "species_description", nullable = false, unique = true)
    private String speciesDescription;

    @Column(name = "species_image")
    private String speciesImage;

    // Getters and Setters

}
