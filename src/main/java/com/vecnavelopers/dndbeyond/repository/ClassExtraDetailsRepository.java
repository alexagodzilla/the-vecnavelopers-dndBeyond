package com.vecnavelopers.dndbeyond.repository;

import com.vecnavelopers.dndbeyond.model.ClassExtraDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClassExtraDetailsRepository extends JpaRepository<ClassExtraDetails, Long> {

//    ClassExtraDetails findByClassName(String className);

    @Query("SELECT c FROM ClassExtraDetails c WHERE LOWER(c.className) = LOWER(:className)")
    ClassExtraDetails findByClassName(@Param("className") String className);
}
