package com.example.SystemAnalysisDesign.user.controller;

import com.example.SystemAnalysisDesign.common.dto.reqeust.SuccessResponse;
import com.example.SystemAnalysisDesign.common.dto.reqeust.result.ListResult;
import com.example.SystemAnalysisDesign.common.dto.reqeust.result.SingleResult;
import com.example.SystemAnalysisDesign.user.controller.dto.request.LoginDto;
import com.example.SystemAnalysisDesign.user.controller.dto.response.UserResponse;
import com.example.SystemAnalysisDesign.user.domain.dto.UserCreateDto;
import com.example.SystemAnalysisDesign.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "유저(user)")
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    @Operation(summary = "회원 가입")
    public SuccessResponse<SingleResult<UserResponse>> register(@Valid @RequestBody UserCreateDto dto) {
        SingleResult<UserResponse> result = userService.join(dto, bCryptPasswordEncoder);
        return SuccessResponse.ok(result);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    public SuccessResponse<SingleResult<Long>> login(@Valid @RequestBody LoginDto dto) {
        SingleResult<Long> result = userService.login(dto);
        return SuccessResponse.ok(result);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "회원 단건 조회")
    public SuccessResponse<SingleResult<UserResponse>> readOne(@PathVariable("userId") Long id) {
        SingleResult<UserResponse> result = userService.getById(id);
        return SuccessResponse.ok(result);
    }

    @GetMapping
    @Operation(summary = "회원 목록 조회")
    public SuccessResponse<ListResult<UserResponse>> readAll() {
        ListResult<UserResponse> result = userService.findAll();
        return SuccessResponse.ok(result);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "회원 삭제 - Hard Delete")
    public SuccessResponse<SingleResult<Long>> delete(@PathVariable("userId") Long id) {
        SingleResult<Long> result = userService.delete(id);
        return SuccessResponse.ok(result);
    }
}
