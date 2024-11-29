package com.example.SystemAnalysisDesign.post.controller;

import com.example.SystemAnalysisDesign.common.dto.reqeust.SuccessResponse;
import com.example.SystemAnalysisDesign.common.dto.reqeust.result.SingleResult;
import com.example.SystemAnalysisDesign.post.controller.dto.reponse.PostResponse;
import com.example.SystemAnalysisDesign.post.controller.dto.request.AssociateKeywordToPostDto;
import com.example.SystemAnalysisDesign.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@Tag(name = "모집글(post)")
public class PostKeywordController {

    private final PostService postService;

    @PostMapping("/associate-keyword")
    @Operation(summary = "모집글에 키워드 등록")
    public SuccessResponse<SingleResult<PostResponse>> addKeywordToPost(@Valid @RequestBody AssociateKeywordToPostDto request) {
        SingleResult<PostResponse> result = postService.addKeywordToPost(request.getPostId(), request.getKeywordId());
        return SuccessResponse.ok(result);
    }
}
