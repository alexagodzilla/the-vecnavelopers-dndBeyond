package com.vecnavelopers.dndbeyond.repository;

import com.vecnavelopers.dndbeyond.model.ClassExtraDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassExtraDetailsRepository extends JpaRepository<ClassExtraDetails, Long> {

    ClassExtraDetails findByClassName(String className);
}
