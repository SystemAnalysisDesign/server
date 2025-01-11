package com.example.SystemAnalysisDesign.user.service;

import com.example.SystemAnalysisDesign.mock.FakeUserRepository;
import com.example.SystemAnalysisDesign.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {

    FakeUserRepository fakeUserRepository = new FakeUserRepository();

    @BeforeEach
    void init() {
        fakeUserRepository.save(createUser(1L, "kjk1@naver.com", "1234"));
        fakeUserRepository.save(createUser(2L, "kjk2@naver.com", "1234"));
    }

    private User createUser(Long id, String email, String password) {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();
    }

    @Test
    public void ID로_유저를_조회할_수_있다() {
        // given
        Long userId = 1L;

        // when
        Optional<User> findUserOptional = fakeUserRepository.findById(userId);

        // then
        assertThat(findUserOptional).isPresent();
        User findUser = findUserOptional.get();
        assertThat(findUser.getId()).isEqualTo(userId);
        assertThat(findUser.getEmail()).isEqualTo("kjk1@naver.com");
        assertThat(findUser.getPassword()).isEqualTo("1234");
    }

    @Test
    public void 존재하지_않는_ID로_조회하면_Optional이_비어있다() {
        // given
        Long invalidId = 999L;

        // when
        Optional<User> findUserOptional = fakeUserRepository.findById(invalidId);

        // then
        assertThat(findUserOptional).isNotPresent(); // Optional이 비어 있는지 확인
    }

    @Test
    public void 이메일로_유저를_조회할_수_있다() {
        // given
        String email = "kjk1@naver.com";

        // when
        Optional<User> findUserOptional = fakeUserRepository.findByEmail(email);

        // then
        assertThat(findUserOptional).isPresent();
        User findUser = findUserOptional.get();
        assertThat(findUser.getEmail()).isEqualTo(email);
        assertThat(findUser.getId()).isEqualTo(1L);
    }


}