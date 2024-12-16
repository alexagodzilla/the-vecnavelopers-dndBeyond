package com.vecnavelopers.dndbeyond.repository;

import com.vecnavelopers.dndbeyond.model.Background;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BackgroundRepository extends JpaRepository<Background, Long> {

    Background findByBackgroundName(String backgroundName);

    Optional<Background> findByBackgroundNameIgnoreCase(String backgroundName);
}
