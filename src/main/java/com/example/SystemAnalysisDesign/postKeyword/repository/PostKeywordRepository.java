package com.example.SystemAnalysisDesign.postKeyword.repository;

import com.example.SystemAnalysisDesign.postKeyword.domain.PostKeyword;

import java.util.List;

public interface PostKeywordRepository {

    PostKeyword save(PostKeyword postKeyword);

    List<PostKeyword> findAll();
}
