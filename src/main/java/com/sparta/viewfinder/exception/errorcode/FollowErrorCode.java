package com.sparta.viewfinder.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FollowErrorCode implements ErrorCode{
    SELF_FOLLOW_ERROR(HttpStatus.BAD_REQUEST,
            "본인을 팔로우할 수 없습니다.");
    private final HttpStatus status;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return this.status;
    }
}
