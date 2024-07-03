package com.sparta.viewfinder.profile;

import com.sparta.viewfinder.profile.dto.ProfileAllResponseDto;
import com.sparta.viewfinder.profile.dto.ProfileDetailResponseDto;
import com.sparta.viewfinder.profile.dto.ProfileUpdateRequestDto;
import com.sparta.viewfinder.profile.dto.ProfileUpdateResponseDto;
import com.sparta.viewfinder.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<List<ProfileAllResponseDto>> getAllProfiles() {
        List<ProfileAllResponseDto> allProfiles = profileService.getAllProfiles();
        return ResponseEntity.ok().body(allProfiles);
    }

    @GetMapping("/{profile_id}")
    public ResponseEntity<ProfileDetailResponseDto> getProfileDetail(@PathVariable("profile_id") Long profileId) {
        ProfileDetailResponseDto profileDetail = profileService.getProfileDetail(profileId);
        return ResponseEntity.ok().body(profileDetail);
    }

    @PatchMapping
    public ResponseEntity<ProfileUpdateResponseDto> updateProfile(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody ProfileUpdateRequestDto requestDto) {
        ProfileUpdateResponseDto profileUpdateResponseDto = profileService.updateProfile(userDetails, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(profileUpdateResponseDto);
    }
}
