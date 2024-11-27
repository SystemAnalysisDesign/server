package com.example.SystemAnalysisDesign.user.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginDto {

    private final String email;
    private final String password;

    @Builder
    public LoginDto(
            @JsonProperty("email") String email,
            @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }
}
