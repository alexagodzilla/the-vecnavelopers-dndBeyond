package com.vecnavelopers.dndbeyond.repository;

import com.vecnavelopers.dndbeyond.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends JpaRepository<Character, Long>{
    @Query("SELECT c FROM Character c WHERE c.userId = :userId")
    List<Character> findCharactersByUserId(@Param("userId") Long userId);
}
