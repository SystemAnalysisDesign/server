package com.example.SystemAnalysisDesign.user.repository;


import com.example.SystemAnalysisDesign.postKeyword.domain.PostKeyword;
import com.example.SystemAnalysisDesign.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User getByEmail(String email);

    Optional<User> findById(long id);

    Optional<User> findByEmail(String email);

    User save(User user);

    List<User> findAll();

    Long deleteById(long id);

    List<User> findUsersByKeywords(List<PostKeyword> keywords);
}
