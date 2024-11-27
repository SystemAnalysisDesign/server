package com.example.SystemAnalysisDesign.post.controller;

import com.example.SystemAnalysisDesign.common.dto.reqeust.SuccessResponse;
import com.example.SystemAnalysisDesign.common.dto.reqeust.result.ListResult;
import com.example.SystemAnalysisDesign.common.dto.reqeust.result.SingleResult;
import com.example.SystemAnalysisDesign.common.service.ResponseService;
import com.example.SystemAnalysisDesign.post.controller.dto.PostResponse;
import com.example.SystemAnalysisDesign.post.domain.dto.PostCreateDto;
import com.example.SystemAnalysisDesign.post.domain.dto.PostUpdateDto;
import com.example.SystemAnalysisDesign.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@Tag(name = "모집글(post)")
public class PostController {

    private final PostService postService;

    @PostMapping
    @Operation(summary = "모집글 생성")
    public SuccessResponse<SingleResult<PostResponse>> create(@Valid @RequestBody PostCreateDto dto) {
        SingleResult<PostResponse> result = postService.create(dto);
        return SuccessResponse.ok(result);
    }

    @GetMapping("/{postId}")
    @Operation(summary = "모집글 단건 조회")
    public SuccessResponse<SingleResult<PostResponse>> readOne(@PathVariable("postId") Long postId) {
        SingleResult<PostResponse> result = postService.getById(postId);
        return SuccessResponse.ok(result);
    }

    @GetMapping
    @Operation(summary = "모집글 목록 조회")
    public SuccessResponse<ListResult<PostResponse>> readAll() {
        ListResult<PostResponse> result = postService.findAll();
        return SuccessResponse.ok(result);
    }

    @PutMapping("/{postId}")
    @Operation(summary = "모집글 수정")
    public SuccessResponse<SingleResult<PostResponse>> update(@PathVariable("postId") Long postId, PostUpdateDto dto) {
        SingleResult<PostResponse> result = postService.update(postId, dto);
        return SuccessResponse.ok(result);
    }

    @DeleteMapping("/{postId}")
    @Operation(summary = "모집글 삭제 - Hard Delete")
    public SuccessResponse<SingleResult<Long>> delete(@PathVariable("postId") Long postId) {
        SingleResult<Long> result = postService.delete(postId);
        return SuccessResponse.ok(result);
    }
}
