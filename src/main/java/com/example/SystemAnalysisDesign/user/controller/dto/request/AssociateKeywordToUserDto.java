package com.example.SystemAnalysisDesign.user.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AssociateKeywordToUserDto {

    @Schema(description = "유저 아이디", example = "1")
    private final Long userId;

    @Schema(description = "키워드 아이디", example = "1")
    private final Long keywordId;

    @Builder
    public AssociateKeywordToUserDto(
            @JsonProperty("userId") Long userId,
            @JsonProperty("keywordId") Long keywordId) {
        this.userId = userId;
        this.keywordId = keywordId;
    }
}
