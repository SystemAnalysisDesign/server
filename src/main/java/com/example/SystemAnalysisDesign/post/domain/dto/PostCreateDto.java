package com.example.SystemAnalysisDesign.post.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "모집글 생성 요청 DTO")
public class PostCreateDto {

    @Schema(description = "제목", example = "제목입니다")
    private final String title;

    @Schema(description = "내용", example = "내용입니다")
    private final String content;

    @Schema(description = "작성자 유저 ID", example = "1")
    private final Long userId;

    @Schema(description = "모집글에 해당하는 키워드 ID", example = "[1, 2, 3]")
    private final List<Long> keywordsIds;

    @Builder
    public PostCreateDto(
            @JsonProperty("title") String title,
            @JsonProperty("content") String content,
            @JsonProperty("userId") long userId,
            @JsonProperty("keywordsIds") List<Long> keywordsIds) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.keywordsIds = keywordsIds;
    }
}
