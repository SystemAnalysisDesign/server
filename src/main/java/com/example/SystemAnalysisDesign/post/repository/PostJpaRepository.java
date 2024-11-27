package com.example.SystemAnalysisDesign.post.repository;

import com.example.SystemAnalysisDesign.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long> {
}
