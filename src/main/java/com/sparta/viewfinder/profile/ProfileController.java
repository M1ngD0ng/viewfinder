package com.sparta.viewfinder.profile;

import com.sparta.viewfinder.profile.dto.*;
import com.sparta.viewfinder.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/profile")
    public ResponseEntity<List<ProfileAllResponseDto>> getAllProfiles() {
        List<ProfileAllResponseDto> allProfiles = profileService.getAllProfiles();
        return ResponseEntity.ok().body(allProfiles);
    }


    @GetMapping("/profile/{profile_id}")
    public ResponseEntity<ProfileDetailResponseDto> getProfileDetail(@PathVariable("profile_id") Long profileId) {
        ProfileDetailResponseDto profileDetail = profileService.getProfileDetail(profileId);
        return ResponseEntity.ok().body(profileDetail);
    }

    @PatchMapping("/mypage")
    public ResponseEntity<ProfileUpdateResponseDto> updateProfile(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody ProfileUpdateRequestDto requestDto) {
        ProfileUpdateResponseDto profileUpdateResponseDto = profileService.updateProfile(userDetails, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(profileUpdateResponseDto);
    }

    @GetMapping("/mypage")
    public ResponseEntity<ProfileMyPageResponseDto> getMyPage(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(profileService.getMyPage(userDetails.getUser()));
    }
}
