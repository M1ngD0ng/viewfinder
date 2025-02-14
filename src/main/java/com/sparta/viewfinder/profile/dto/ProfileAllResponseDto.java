package com.sparta.viewfinder.profile.dto;

import com.sparta.viewfinder.profile.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileAllResponseDto {
    private Long userId;
    private String name;
    private String headline;


    public ProfileAllResponseDto(Profile profile) {
        this.userId = profile.getId();
        this.name = profile.getUser().getName();
        this.headline = profile.getHeadline();
    }
}
