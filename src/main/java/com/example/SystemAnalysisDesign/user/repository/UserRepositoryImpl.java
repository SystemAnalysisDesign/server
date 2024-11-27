package com.example.SystemAnalysisDesign.user.repository;

import com.example.SystemAnalysisDesign.common.exception.CustomException;
import com.example.SystemAnalysisDesign.common.exception.ErrorCode;
import com.example.SystemAnalysisDesign.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User getByEmail(String email) {
        return findByEmail(email).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_EXIST));
    }

    @Override
    public Optional<User> findById(long id) {
        return userJpaRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll();
    }

    @Override
    public Long deleteById(long id) {
        userJpaRepository.deleteById(id);
        return id;
    }
}
