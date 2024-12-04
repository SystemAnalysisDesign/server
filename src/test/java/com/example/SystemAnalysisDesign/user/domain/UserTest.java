package com.example.SystemAnalysisDesign.user.domain;

import com.example.SystemAnalysisDesign.user.domain.dto.UserCreateDto;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    
    @Test
    public void UserCreate로_유저_생성() {
        //given
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .email("kjk@naver.com")
                .password("1234")
                .build();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //when
        User user = User.from(userCreateDto, encoder);
        
        //then
        assertThat(user.getId()).isNull();
        assertThat(user.getEmail()).isEqualTo("kjk@naver.com");
        assertThat(encoder.matches("1234", user.getPassword())).isTrue();
    }
}