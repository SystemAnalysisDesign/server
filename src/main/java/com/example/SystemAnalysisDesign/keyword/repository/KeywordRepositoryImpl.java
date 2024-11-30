package com.example.SystemAnalysisDesign.keyword.repository;

import com.example.SystemAnalysisDesign.common.exception.CustomException;
import com.example.SystemAnalysisDesign.common.exception.ErrorCode;
import com.example.SystemAnalysisDesign.keyword.domain.Keyword;
import com.example.SystemAnalysisDesign.postKeyword.domain.PostKeyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.security.Key;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class KeywordRepositoryImpl implements KeywordRepository {

    private final KeywordJpaRepository keywordJpaRepository;

    @Override
    public Keyword getById(long id) {
        return findById(id).orElseThrow(() -> new CustomException(ErrorCode.KEYWORD_NOT_EXIST));
    }

    @Override
    public Keyword getByName(String name) {
        return findByName(name).orElseThrow(() -> new CustomException(ErrorCode.KEYWORD_NOT_EXIST));
    }

    @Override
    public Optional<Keyword> findByName(String name) {
        return keywordJpaRepository.findByName(name);
    }

    @Override
    public Optional<Keyword> findById(long id) {
        return keywordJpaRepository.findById(id);
    }

    @Override
    public Keyword save(Keyword keyword) {
        return keywordJpaRepository.save(keyword);
    }

    @Override
    public List<Keyword> findAll() {
        return keywordJpaRepository.findAll();
    }

    @Override
    public Long deleteById(long id) {
        keywordJpaRepository.deleteById(id);
        return id;
    }

    @Override
    public List<Keyword> findAllById(Iterable<Long> ids) {
        return keywordJpaRepository.findAllById(ids);
    }
}
