package com.example.SystemAnalysisDesign.keyword.controller.dto;

import com.example.SystemAnalysisDesign.keyword.domain.Keyword;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KeywordResponse {

    private Long id;
    private String name;

    public static KeywordResponse from(Keyword keyword) {
        return KeywordResponse.builder()
                .id(keyword.getId())
                .name(keyword.getName())
                .build();
    }
}
