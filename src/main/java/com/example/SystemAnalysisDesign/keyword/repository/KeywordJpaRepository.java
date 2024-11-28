package com.example.SystemAnalysisDesign.keyword.repository;

import com.example.SystemAnalysisDesign.keyword.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KeywordJpaRepository extends JpaRepository<Keyword, Long> {

    Optional<Keyword> findByName(String name);
}
