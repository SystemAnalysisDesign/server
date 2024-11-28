package com.example.SystemAnalysisDesign.keyword.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(name = "키워드 생성 요청 DTO")
public class KeywordCreateDto {

    @Schema(description = "키워드명", example = "키워드_이름")
    private final String name;

    @Builder
    public KeywordCreateDto(
            @JsonProperty("name") String name
    ) {
        this.name = name;
    }
}
