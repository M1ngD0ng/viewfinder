package com.sparta.viewfinder.like;

import com.sparta.viewfinder.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/posts/{contentId}/like")
    public ResponseEntity<LikeResponseDto> postLikeToggle(@PathVariable("contentId") Long contentId,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(likeService.postLikeToggle(contentId, userDetails.getUser()));
    }
    @PostMapping("/comments/{contentId}/like")
    public ResponseEntity<LikeResponseDto> commentLikeToggle(@PathVariable("contentId") Long contentId,
                                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(likeService.commentLikeToggle(contentId, userDetails.getUser()));
    }
}
