package com.example.SystemAnalysisDesign.keyword.repository;

import com.example.SystemAnalysisDesign.keyword.domain.Keyword;

import java.util.List;
import java.util.Optional;

public interface KeywordRepository {

    Keyword getById(long id);

    Keyword getByName(String name);

    Optional<Keyword> findByName(String name);

    Optional<Keyword> findById(long id);

    Keyword save(Keyword keyword);

    List<Keyword> findAll();

    Long deleteById(long id);

    List<Keyword> findAllById(Iterable<Long> ids);
}
