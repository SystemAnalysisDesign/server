package com.example.SystemAnalysisDesign.userKeyword.Repository;

import com.example.SystemAnalysisDesign.userKeyword.domain.UserKeyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserKeywordRespositoryImpl implements UserKeywordRepository{

    private final UserKeywordJpaRepository userKeywordJpaRepository;

    @Override
    public UserKeyword save(UserKeyword userKeyword) {
        return userKeywordJpaRepository.save(userKeyword);
    }

    @Override
    public List<UserKeyword> findAll() {
        return userKeywordJpaRepository.findAll();
    }

    @Override
    public List<UserKeyword> findAllByUser_Id(Long userId) {
        return userKeywordJpaRepository.findAllByUser_Id(userId);
    }
}
