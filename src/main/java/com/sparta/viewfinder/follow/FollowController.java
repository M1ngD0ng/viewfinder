package com.sparta.viewfinder.follow;

import com.sparta.viewfinder.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follows")
public class FollowController {
    private final FollowService followService;

    @PostMapping("/{userId}")
    public ResponseEntity<String> followToggle(@PathVariable("userId") Long userId,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(followService.followToggle(userId, userDetails.getUser()));
    }
}
