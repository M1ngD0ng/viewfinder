package com.sparta.viewfinder.service;

import com.sparta.viewfinder.dto.ProfileAllResponseDto;
import com.sparta.viewfinder.dto.ProfileDetailResponseDto;
import com.sparta.viewfinder.dto.ProfileUpdateRequestDto;
import com.sparta.viewfinder.dto.ProfileUpdateResponseDto;
import com.sparta.viewfinder.entity.Profile;
import com.sparta.viewfinder.entity.User;
import com.sparta.viewfinder.exception.CommonErrorCode;
import com.sparta.viewfinder.exception.NotFoundException;
import com.sparta.viewfinder.exception.UserErrorCode;
import com.sparta.viewfinder.repository.ProfileRepository;
import com.sparta.viewfinder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public List<ProfileAllResponseDto> getAllProfiles() {
        List<Profile> profileAllResponseDtoList = profileRepository.findAll();
        return profileAllResponseDtoList.stream().map(ProfileAllResponseDto::new).toList();
    }

    public ProfileDetailResponseDto getProfileDetail(Long profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        return new ProfileDetailResponseDto(profile);
    }

    @Transactional
    public ProfileUpdateResponseDto updateProfile(Long userId, ProfileUpdateRequestDto profileUpdateRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new NotFoundException(UserErrorCode.USER_NOT_FOUND)
        );
        Profile profile = profileRepository.findByUserId(user.getId()).orElseThrow(
                ()-> new NotFoundException(CommonErrorCode.RESOURCE_NOT_FOUND)
        );

        profile.update(profileUpdateRequestDto);
        return new ProfileUpdateResponseDto(profile);
    }
}
