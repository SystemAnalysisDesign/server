package com.example.SystemAnalysisDesign.post.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "모집글 수정 요청 DTO")
public class PostUpdateDto {

    @Schema(description = "제목", example = "수정한 제목입니다")
    private final String title;

    @Schema(description = "내용", example = "수정한 내용입니다")
    private final String content;

    @Builder
    public PostUpdateDto(
            @JsonProperty("title") String title,
            @JsonProperty("content") String content) {
        this.title = title;
        this.content = content;
    }
}
