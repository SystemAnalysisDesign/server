package com.example.SystemAnalysisDesign.keyword.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(name = "키워드 수정 요청 DTO")
public class KeywordUpdateDto {

    @Schema(description = "키워드명", example = "수정_키워드_이름")
    private final String name;

    @Builder
    public KeywordUpdateDto(
            @JsonProperty("name") String name
    ) {
        this.name = name;
    }
}
