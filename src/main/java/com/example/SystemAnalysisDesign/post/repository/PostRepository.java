package com.example.SystemAnalysisDesign.post.repository;

import com.example.SystemAnalysisDesign.post.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post getById(long id);

    Optional<Post> findById(long id);

    Post save(Post post);

    List<Post> findAll();

    Long deleteById(long id);
}
