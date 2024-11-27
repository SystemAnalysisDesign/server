package com.example.SystemAnalysisDesign.post.service;

import com.example.SystemAnalysisDesign.common.dto.reqeust.result.ListResult;
import com.example.SystemAnalysisDesign.common.dto.reqeust.result.SingleResult;
import com.example.SystemAnalysisDesign.common.exception.CustomException;
import com.example.SystemAnalysisDesign.common.exception.ErrorCode;
import com.example.SystemAnalysisDesign.common.service.ResponseService;
import com.example.SystemAnalysisDesign.post.controller.dto.PostResponse;
import com.example.SystemAnalysisDesign.post.domain.Post;
import com.example.SystemAnalysisDesign.post.domain.dto.PostCreateDto;
import com.example.SystemAnalysisDesign.post.domain.dto.PostUpdateDto;
import com.example.SystemAnalysisDesign.post.repository.PostRepository;
import com.example.SystemAnalysisDesign.user.domain.User;
import com.example.SystemAnalysisDesign.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public SingleResult<PostResponse> create(PostCreateDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_EXIST));
        Post post = postRepository.save(Post.from(dto, user));
        return ResponseService.getSingleResult(PostResponse.from(post));
    }

    public SingleResult<PostResponse> getById(long id) {
        Post post = postRepository.getById(id);
        return ResponseService.getSingleResult(PostResponse.from(post));
    }

    public ListResult<PostResponse> findAll() {
        List<Post> posts = postRepository.findAll();

        List<PostResponse> result = posts.stream()
                .map(PostResponse::from)
                .collect(Collectors.toList());

        return ResponseService.getListResult(result);
    }

    @Transactional
    public SingleResult<PostResponse> update(Long id, PostUpdateDto dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_EXIST));

        Post updatePost = post.update(dto);
        return ResponseService.getSingleResult(PostResponse.from(updatePost));
    }

    @Transactional
    public SingleResult<Long> delete(long id) {
        postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_EXIST));
        long result = postRepository.deleteById(id);
        return ResponseService.getSingleResult(result);
    }
}
