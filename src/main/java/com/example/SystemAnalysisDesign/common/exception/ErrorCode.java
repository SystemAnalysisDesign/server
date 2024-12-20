package com.example.SystemAnalysisDesign.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     * 에러코드 규약
     * HTTP Status Code는 에러에 가장 유사한 코드를 부여한다.
     * 사용자정의 에러코드는 음수를 사용한다.
     * 사용자정의 에러코드는 중복되지 않게 배정한다.
     * 사용자정의 에러코드는 각 카테고리 별로 100단위씩 끊어서 배정한다. 단, Common 카테고리는 -100 단위를 고정으로 가져간다.
     */

    /**
     * 401 : 미승인
     * 403 : 권한의 문제가 있을때
     * 406 : 객체가 조회되지 않을 때
     * 409 : 현재 데이터와 값이 충돌날 때(ex. 아이디 중복)
     * 412 : 파라미터 값이 뭔가 누락됐거나 잘못 왔을 때
     * 422 : 파라미터 문법 오류
     * 424 : 뭔가 단계가 꼬였을때, 1번안하고 2번하고 그런경우
     */

    // Common
    SERVER_UNTRACKED_ERROR(-100, "미등록 서버 에러입니다. 서버 팀에 연락주세요.", 500),
    OBJECT_NOT_FOUND(-101, "조회된 객체가 없습니다.", 406),
    INVALID_PARAMETER(-102, "잘못된 파라미터입니다.",422),
    PARAMETER_VALIDATION_ERROR(-103, "파라미터 검증 에러입니다.",422),
    PARAMETER_GRAMMAR_ERROR(-104, "파라미터 문법 에러입니다.",422),

    // User
    USER_ALREADY_EXIST(-300, "이미 회원가입된 유저입니다.", 400),
    USER_NOT_EXIST(-301, "존재하지 않는 유저입니다.", 406),
    USER_WRONG_PASSWORD(-302, "비밀번호가 틀렸습니다.", 401),

    // Post
    POST_NOT_EXIST(-400,"존재하지 않는 모집글입니다.",406),

    // Keyword
    KEYWORD_ALREADY_EXIST(-500, "이미 존재하는 키워드입니다.", 400),
    KEYWORD_NOT_EXIST(-501, "존재하지 않는 키워드입니다.", 406),
    DUPLICATE_KEYWORD(-502, "회원은 이미 해당 키워드를 가지고 있습니다.", 409);

    private final int errorCode;
    private final String message;
    private final int httpCode;
}
