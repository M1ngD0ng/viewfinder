package com.sparta.viewfinder.follow;

import com.sparta.viewfinder.exception.errorcode.FollowErrorCode;
import com.sparta.viewfinder.exception.exception.CommonException;
import com.sparta.viewfinder.post.dto.PostResponseDto;
import com.sparta.viewfinder.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follows")
public class FollowController {
    private final FollowService followService;
    private static final String SORT_INVALID = "유효하지 않은 정렬 기준입니다.";

    @PostMapping("/{userId}")
    public ResponseEntity<String> followToggle(@PathVariable("userId") Long userId,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(followService.followToggle(userId, userDetails.getUser()));
    }

    @GetMapping("/posts")
    public ResponseEntity<Page<PostResponseDto>> getFollowedPost(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                 @RequestParam(value = "sortBy", defaultValue = "createdAt") String sortBy,
                                                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {

        List<String> sortable = Arrays.asList("createdAt", "name"); // 생성일자와 좋아요순으로만 조회 가능
        if (!sortable.contains(sortBy)){
            throw new CommonException(FollowErrorCode.NOT_SORTABLE);
        }
        return ResponseEntity.ok(followService.getFollowedPost(page - 1, sortBy, userDetails.getUser()));
    }
}
