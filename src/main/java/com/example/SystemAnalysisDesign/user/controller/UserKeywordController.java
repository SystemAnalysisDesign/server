package com.example.SystemAnalysisDesign.user.controller;

import com.example.SystemAnalysisDesign.common.dto.reqeust.SuccessResponse;
import com.example.SystemAnalysisDesign.common.dto.reqeust.result.SingleResult;
import com.example.SystemAnalysisDesign.user.controller.dto.request.AssociateKeywordDto;
import com.example.SystemAnalysisDesign.user.controller.dto.response.UserResponse;
import com.example.SystemAnalysisDesign.user.domain.dto.UserCreateDto;
import com.example.SystemAnalysisDesign.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "유저(user)")
public class UserKeywordController {

    private final UserService userService;

    @PostMapping("/associate-keyword")
    @Operation(summary = "회원에 키워드 등록")
    public SuccessResponse<SingleResult<UserResponse>> addKeywordToUser(@Valid @RequestBody AssociateKeywordDto request) {
        SingleResult<UserResponse> result = userService.addKeywordToUser(request.getUserId(), request.getKeywordId());
        return SuccessResponse.ok(result);
    }
}
