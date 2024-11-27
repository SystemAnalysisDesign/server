package com.example.SystemAnalysisDesign.post.repository;

import com.example.SystemAnalysisDesign.common.exception.CustomException;
import com.example.SystemAnalysisDesign.common.exception.ErrorCode;
import com.example.SystemAnalysisDesign.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final PostJpaRepository postJpaRepository;

    @Override
    public Post getById(long id) {
        return findById(id).orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_EXIST));
    }

    @Override
    public Optional<Post> findById(long id) {
        return postJpaRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postJpaRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return postJpaRepository.findAll();
    }

    @Override
    public Long deleteById(long id) {
        postJpaRepository.deleteById(id);
        return id;
    }
}
