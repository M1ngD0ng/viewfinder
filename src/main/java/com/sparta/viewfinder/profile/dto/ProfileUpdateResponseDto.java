package com.sparta.viewfinder.profile.dto;

import lombok.Getter;

import com.sparta.viewfinder.profile.Profile;
import java.time.LocalDateTime;

@Getter
public class ProfileUpdateResponseDto {
    private Long userId;
    private String headline;
    private String phoneNumber;
    private String sns;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;


    public ProfileUpdateResponseDto(Profile profile) {
        this.userId = profile.getId();
        this.headline = profile.getHeadline();
        this.phoneNumber = profile.getPhoneNumber();
        this.sns = profile.getSns();
        this.createAt = profile.getCreatedAt();
        this.modifiedAt = profile.getModifiedAt();
    }
}
