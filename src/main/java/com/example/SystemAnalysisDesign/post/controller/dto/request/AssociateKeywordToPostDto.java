package com.example.SystemAnalysisDesign.post.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AssociateKeywordToPostDto {

    @Schema(description = "모집글 아이디", example = "1")
    private final Long postId;

    @Schema(description = "키워드 아이디", example = "1")
    private final Long keywordId;

    @Builder
    public AssociateKeywordToPostDto(
            @JsonProperty("postId") Long postId,
            @JsonProperty("keywordId") Long keywordId) {
        this.postId = postId;
        this.keywordId = keywordId;
    }
}
