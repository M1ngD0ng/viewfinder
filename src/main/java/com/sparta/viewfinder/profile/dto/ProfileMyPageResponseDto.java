package com.sparta.viewfinder.profile.dto;

import com.sparta.viewfinder.profile.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class ProfileMyPageResponseDto {
    private Long userId;
    private String name;
    private String headline;
    private String email;
    private String phoneNumber;
    private String sns;
    private Long countLikedPost;
    private Long countLikedComment;


    public ProfileMyPageResponseDto(Profile profile, Long countLikedPost, Long countLikedComment) {
        this.userId = profile.getId();
        this.name = profile.getUser().getName();
        this.headline = profile.getHeadline();
        this.email = profile.getUser().getEmail();
        this.phoneNumber = profile.getPhoneNumber();
        this.sns = profile.getSns();
        this.countLikedPost = countLikedPost;
        this.countLikedComment = countLikedComment;
    }
}
