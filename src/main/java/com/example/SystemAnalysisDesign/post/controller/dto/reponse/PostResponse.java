package com.example.SystemAnalysisDesign.post.controller.dto.reponse;

import com.example.SystemAnalysisDesign.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class PostResponse {

    private Long id;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private List<String> keywords;

    public static PostResponse from(Post post) {
        List<String> keywordNames = post.getPostKeywords().stream()
                .map(postKeyword -> postKeyword.getKeyword().getName())
                .toList();

        return PostResponse.builder()
                .id(post.getId())
                .userId(post.getUser().getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .keywords(keywordNames)
                .build();
    }
}
