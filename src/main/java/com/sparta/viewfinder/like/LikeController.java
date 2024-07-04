package com.sparta.viewfinder.like;

import com.sparta.viewfinder.post.dto.PostResponseDto;
import com.sparta.viewfinder.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/posts/{contentId}/like")
    public ResponseEntity<LikeResponseDto> postLikeToggle(@PathVariable("contentId") Long contentId,
                                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(likeService.postLikeToggle(contentId, userDetails.getUser()));
    }

    @PostMapping("/posts/{postId}/comments/{contentId}/like")
    public ResponseEntity<LikeResponseDto> commentLikeToggle(@PathVariable("postId") Long postId,
                                                             @PathVariable("contentId") Long contentId,
                                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(likeService.commentLikeToggle(contentId, userDetails.getUser()));
    }

    @GetMapping("/likes/posts")
    public ResponseEntity<Page<PostResponseDto>> getLikedPost(@RequestParam(value = "page", defaultValue = "1") int page,
                                                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(likeService.getLikedPost(page - 1, userDetails.getUser()));
    }
}
