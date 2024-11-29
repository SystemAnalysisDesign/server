package com.example.SystemAnalysisDesign.postKeyword.repository;

import com.example.SystemAnalysisDesign.postKeyword.domain.PostKeyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostKeywordRepositoryImpl implements PostKeywordRepository {

    private final PostKeywordJpaRepostiory postKeywordJpaRepostiory;

    @Override
    public PostKeyword save(PostKeyword postKeyword) {
        return postKeywordJpaRepostiory.save(postKeyword);
    }

    @Override
    public List<PostKeyword> findAll() {
        return postKeywordJpaRepostiory.findAll();
    }
}
