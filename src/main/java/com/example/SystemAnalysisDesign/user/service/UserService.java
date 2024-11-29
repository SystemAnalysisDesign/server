package com.example.SystemAnalysisDesign.user.service;

import com.example.SystemAnalysisDesign.common.dto.reqeust.result.ListResult;
import com.example.SystemAnalysisDesign.common.dto.reqeust.result.SingleResult;
import com.example.SystemAnalysisDesign.common.exception.CustomException;
import com.example.SystemAnalysisDesign.common.exception.ErrorCode;
import com.example.SystemAnalysisDesign.common.service.ResponseService;
import com.example.SystemAnalysisDesign.keyword.domain.Keyword;
import com.example.SystemAnalysisDesign.keyword.repository.KeywordRepository;
import com.example.SystemAnalysisDesign.user.controller.dto.request.LoginDto;
import com.example.SystemAnalysisDesign.user.controller.dto.response.UserResponse;
import com.example.SystemAnalysisDesign.user.domain.User;
import com.example.SystemAnalysisDesign.user.domain.dto.UserCreateDto;
import com.example.SystemAnalysisDesign.user.repository.UserRepository;
import com.example.SystemAnalysisDesign.userKeyword.Repository.UserKeywordRepository;
import com.example.SystemAnalysisDesign.userKeyword.domain.UserKeyword;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final KeywordRepository keywordRepository;
    private final UserKeywordRepository userKeywordRepository;

    // 중복 회원 검증
    private void validateDuplicateUser(UserCreateDto request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.USER_ALREADY_EXIST);
        }
    }

    private User validateExistUser(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_EXIST));
    }

    private Keyword vaildateExistKeyword(Long keywordId) {
        return keywordRepository.findById(keywordId)
                .orElseThrow(() -> new CustomException(ErrorCode.KEYWORD_NOT_EXIST));
    }

    @Transactional
    public SingleResult<UserResponse> join(UserCreateDto dto, BCryptPasswordEncoder encoder) {
        validateDuplicateUser(dto);
        User user = userRepository.save(User.from(dto, encoder));
        return ResponseService.getSingleResult(UserResponse.from(user));
    }

    public SingleResult<UserResponse> getById(long id) {
        User user = validateExistUser(id);
        return ResponseService.getSingleResult(UserResponse.from(user));
    }

    public SingleResult<UserResponse> getByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_EXIST));
        return ResponseService.getSingleResult(UserResponse.from(user));
    }

    public ListResult<UserResponse> findAll() {
        List<User> users = userRepository.findAll();

        List<UserResponse> response = users.stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());

        return ResponseService.getListResult(response);
    }

    @Transactional
    public SingleResult<Long> delete(long id) {
        validateExistUser(id);
        long result = userRepository.deleteById(id);
        return ResponseService.getSingleResult(result);
    }

    // FIXME - 제대로 된 로그인은 추후 스프링 시큐리티 적용 후에
    public SingleResult<Long> login(LoginDto dto) {
        User user = userRepository.getByEmail(dto.getEmail());
        return ResponseService.getSingleResult(user.getId());
    }

    @Transactional
    public SingleResult<UserResponse> addKeywordToUser(Long userId, Long keywordId) {
        User user = validateExistUser(userId);
        Keyword keyword = vaildateExistKeyword(keywordId);

        // 해당 키워드가 유저에게 이미 있는지 이름으로 중복 검증
        boolean isDuplicate = user.getUserKeywords().stream()
                .anyMatch(userKeyword -> userKeyword.getKeyword().equals(keyword));
        if (isDuplicate) {
            throw new CustomException(ErrorCode.DUPLICATE_KEYWORD);
        }

        UserKeyword userKeyword = UserKeyword.builder()
                .user(user)
                .keyword(keyword)
                .build();

        // 연관관계 메서드 설정
        user.addUserKeyword(userKeyword);
        keyword.addUserKeyword(userKeyword);

        userKeywordRepository.save(userKeyword);

        return ResponseService.getSingleResult(UserResponse.from(user));
    }
}
