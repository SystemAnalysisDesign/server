package com.example.SystemAnalysisDesign.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "사용자 생성 요청 DTO")
public class UserCreateDto {

    @Schema(description = "이메일", example = "user@gmail.com")
    private final String email;

    @Schema(description = "비밀번호", example = "password")
    private final String password;

    @Builder
    public UserCreateDto(
            @JsonProperty("email") String email,
            @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }
}
