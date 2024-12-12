package com.vecnavelopers.dndbeyond.repository;

import com.vecnavelopers.dndbeyond.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends JpaRepository<Character, Long>{
}
