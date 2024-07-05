package com.sparta.viewfinder.follow;

import com.sparta.viewfinder.post.dto.PostResponseDto;
import com.sparta.viewfinder.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follows")
public class FollowController {
    private final FollowService followService;

    @PostMapping("/{userId}")
    public ResponseEntity<String> followToggle(@PathVariable("userId") Long userId,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(followService.followToggle(userId, userDetails.getUser()));
    }

    @GetMapping("/posts")
    public ResponseEntity<Page<PostResponseDto>> getFollowedPost(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(followService.getFollowedPost(page - 1, userDetails.getUser()));
    }
}
