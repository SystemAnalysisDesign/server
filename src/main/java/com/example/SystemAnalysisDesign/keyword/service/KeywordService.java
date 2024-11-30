package com.example.SystemAnalysisDesign.keyword.service;

import com.example.SystemAnalysisDesign.common.dto.reqeust.result.ListResult;
import com.example.SystemAnalysisDesign.common.dto.reqeust.result.SingleResult;
import com.example.SystemAnalysisDesign.common.exception.CustomException;
import com.example.SystemAnalysisDesign.common.exception.ErrorCode;
import com.example.SystemAnalysisDesign.common.service.ResponseService;
import com.example.SystemAnalysisDesign.keyword.controller.dto.KeywordResponse;
import com.example.SystemAnalysisDesign.keyword.domain.Keyword;
import com.example.SystemAnalysisDesign.keyword.domain.dto.KeywordCreateDto;
import com.example.SystemAnalysisDesign.keyword.domain.dto.KeywordUpdateDto;
import com.example.SystemAnalysisDesign.keyword.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KeywordService {

    private final KeywordRepository keywordRepository;

    // 키워드 중복 검증 - name
    private void validateDuplicateKeyword(KeywordCreateDto request) {
        if (keywordRepository.findByName(request.getName()).isPresent()) {
            throw new CustomException(ErrorCode.KEYWORD_ALREADY_EXIST);
        }
    }

    @Transactional
    public SingleResult<KeywordResponse> create(KeywordCreateDto dto) {
        validateDuplicateKeyword(dto);
        Keyword keyword = keywordRepository.save(Keyword.from(dto));
        return ResponseService.getSingleResult(KeywordResponse.from(keyword));
    }

    public SingleResult<KeywordResponse> getById(long id) {
        Keyword keyword = keywordRepository.getById(id);
        return ResponseService.getSingleResult(KeywordResponse.from(keyword));
    }

    public ListResult<KeywordResponse> findAll() {
        List<Keyword> keywords = keywordRepository.findAll();

        List<KeywordResponse> result = keywords.stream()
                .map(KeywordResponse::from)
                .collect(Collectors.toList());

        return ResponseService.getListResult(result);
    }

    @Transactional
    public SingleResult<KeywordResponse> update(Long id, KeywordUpdateDto dto) {
        Keyword keyword = keywordRepository.getById(id);

        Keyword updateKeyword = keyword.update(dto);
        return ResponseService.getSingleResult(KeywordResponse.from(updateKeyword));
    }

    @Transactional
    public SingleResult<Long> delete(long id) {
        keywordRepository.getById(id);
        long result = keywordRepository.deleteById(id);
        return ResponseService.getSingleResult(result);
    }
}
