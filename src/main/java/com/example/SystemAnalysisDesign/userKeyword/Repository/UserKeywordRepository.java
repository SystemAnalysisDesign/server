package com.example.SystemAnalysisDesign.userKeyword.Repository;

import com.example.SystemAnalysisDesign.userKeyword.domain.UserKeyword;

import java.util.List;

public interface UserKeywordRepository {

    UserKeyword save(UserKeyword userKeyword);

    List<UserKeyword> findAll();

    List<UserKeyword> findAllByUser_Id(Long userId);
}
