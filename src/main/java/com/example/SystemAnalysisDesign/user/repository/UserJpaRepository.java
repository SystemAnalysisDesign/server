package com.example.SystemAnalysisDesign.user.repository;

import com.example.SystemAnalysisDesign.keyword.domain.Keyword;
import com.example.SystemAnalysisDesign.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT DISTINCT u FROM User u " +
            "JOIN u.userKeywords uk " +
            "WHERE uk.keyword IN :keywords")
    List<User> findUsersByKeywords(@Param("keywords") List<Keyword> keywords);

}
