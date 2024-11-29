package com.example.SystemAnalysisDesign.post.service;

import com.example.SystemAnalysisDesign.common.dto.reqeust.result.ListResult;
import com.example.SystemAnalysisDesign.common.dto.reqeust.result.SingleResult;
import com.example.SystemAnalysisDesign.common.exception.CustomException;
import com.example.SystemAnalysisDesign.common.exception.ErrorCode;
import com.example.SystemAnalysisDesign.common.service.ResponseService;
import com.example.SystemAnalysisDesign.keyword.domain.Keyword;
import com.example.SystemAnalysisDesign.keyword.repository.KeywordRepository;
import com.example.SystemAnalysisDesign.post.controller.dto.reponse.PostResponse;
import com.example.SystemAnalysisDesign.post.domain.Post;
import com.example.SystemAnalysisDesign.post.domain.dto.PostCreateDto;
import com.example.SystemAnalysisDesign.post.domain.dto.PostUpdateDto;
import com.example.SystemAnalysisDesign.post.repository.PostRepository;
import com.example.SystemAnalysisDesign.postKeyword.domain.PostKeyword;
import com.example.SystemAnalysisDesign.postKeyword.repository.PostKeywordRepository;
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
    private final KeywordRepository keywordRepository;
    private final PostKeywordRepository postKeywordRepository;

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

    @Transactional
    public SingleResult<PostResponse> addKeywordToPost(Long postId, Long keywordId) {
        Post post = postRepository.getById(postId);
        Keyword keyword = keywordRepository.getById(keywordId);

        // 해당 키워드가 글에 이미 있는지 이름으로 중복 검증
        boolean isDuplicate = post.getPostKeywords().stream()
                .anyMatch(postKeyword -> postKeyword.getKeyword().equals(keyword));
        if (isDuplicate) {
            throw new CustomException(ErrorCode.DUPLICATE_KEYWORD);
        }

        PostKeyword postKeyword = PostKeyword.builder()
                .post(post)
                .keyword(keyword)
                .build();

        post.addPostKeyword(postKeyword);
        keyword.addPostKeyword(postKeyword);

        postKeywordRepository.save(postKeyword);

        return ResponseService.getSingleResult(PostResponse.from(post));
    }
}
