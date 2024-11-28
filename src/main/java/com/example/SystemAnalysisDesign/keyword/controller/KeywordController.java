package com.example.SystemAnalysisDesign.keyword.controller;

import com.example.SystemAnalysisDesign.common.dto.reqeust.SuccessResponse;
import com.example.SystemAnalysisDesign.common.dto.reqeust.result.ListResult;
import com.example.SystemAnalysisDesign.common.dto.reqeust.result.SingleResult;
import com.example.SystemAnalysisDesign.keyword.controller.dto.KeywordResponse;
import com.example.SystemAnalysisDesign.keyword.domain.dto.KeywordCreateDto;
import com.example.SystemAnalysisDesign.keyword.domain.dto.KeywordUpdateDto;
import com.example.SystemAnalysisDesign.keyword.service.KeywordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/keywords")
@Tag(name = "키워드(keyword)")
public class KeywordController {

    private final KeywordService keywordService;

    @PostMapping
    @Operation(summary = "키워드 생성")
    public SuccessResponse<SingleResult<KeywordResponse>> create(@Valid @RequestBody KeywordCreateDto dto) {
        SingleResult<KeywordResponse> result = keywordService.create(dto);
        return SuccessResponse.ok(result);
    }

    @GetMapping("/{keywordId}")
    @Operation(summary = "키워드 단건 조회")
    public SuccessResponse<SingleResult<KeywordResponse>> readOne(@PathVariable("keywordId") Long keywordId) {
        SingleResult<KeywordResponse> result = keywordService.getById(keywordId);
        return SuccessResponse.ok(result);
    }

    @GetMapping
    @Operation(summary = "키워드 목록 조회")
    public SuccessResponse<ListResult<KeywordResponse>> readAll() {
        ListResult<KeywordResponse> result = keywordService.findAll();
        return SuccessResponse.ok(result);
    }

    @PutMapping("/{keywordId}")
    @Operation(summary = "키워드 수정")
    public SuccessResponse<SingleResult<KeywordResponse>> update(@PathVariable("keywordId") Long keywordId, @Valid @RequestBody KeywordUpdateDto dto) {
        SingleResult<KeywordResponse> result = keywordService.update(keywordId, dto);
        return SuccessResponse.ok(result);
    }

    @DeleteMapping("/{keywordId}")
    @Operation(summary = "키워드 삭제")
    public SuccessResponse<SingleResult<Long>> delete(@PathVariable("keywordId") Long keywordId) {
        SingleResult<Long> result = keywordService.delete(keywordId);
        return SuccessResponse.ok(result);
    }
}
