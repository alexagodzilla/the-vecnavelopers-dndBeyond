package com.vecnavelopers.dndbeyond.repository;

import com.vecnavelopers.dndbeyond.model.SpeciesExtraDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesExtraDetailsRepository extends JpaRepository<SpeciesExtraDetails, Long> {

    SpeciesExtraDetails findBySpeciesName(String speciesName);
}
