package com.vecnavelopers.dndbeyond.repository;

import com.vecnavelopers.dndbeyond.model.SpeciesExtraDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpeciesExtraDetailsRepository extends JpaRepository<SpeciesExtraDetails, Long> {

//    SpeciesExtraDetails findBySpeciesName(String speciesName);

    @Query("SELECT c FROM SpeciesExtraDetails c WHERE LOWER(c.speciesName) = LOWER(:speciesName)")
    SpeciesExtraDetails findBySpeciesName(@Param("speciesName") String speciesName);
}
