package com.example.SystemAnalysisDesign.userKeyword.Repository;

import com.example.SystemAnalysisDesign.userKeyword.domain.UserKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserKeywordJpaRepository extends JpaRepository<UserKeyword, Long> {

    List<UserKeyword> findAllByUser_Id(Long userId);
}
