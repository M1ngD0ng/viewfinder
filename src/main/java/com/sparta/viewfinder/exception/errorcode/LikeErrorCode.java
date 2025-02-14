package com.sparta.viewfinder.exception.errorcode;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum LikeErrorCode implements ErrorCode {
    SELF_LIKE_ERROR(HttpStatus.BAD_REQUEST,
            "본인이 작성한 게시물/댓글에는 좋아요를 누를 수 없습니다.");

    private final HttpStatus status;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return this.status;
    }

}
