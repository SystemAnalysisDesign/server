package com.example.SystemAnalysisDesign.user.controller.dto.response;

import com.example.SystemAnalysisDesign.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserResponse {

    private Long id;
    private String email;
    private List<String> keywords;

    public static UserResponse from(User user) {
        List<String> keywordNames = user.getUserKeywords().stream()
                .map(userKeyword -> userKeyword.getKeyword().getName())
                .toList();

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .keywords(keywordNames)
                .build();
    }
}
