package com.sparta.viewfinder.profile;

import com.sparta.viewfinder.entity.profile.Profile;
import com.sparta.viewfinder.entity.user.User;
import com.sparta.viewfinder.exception.exception.NotFoundException;
import com.sparta.viewfinder.exception.errorcode.UserErrorCode;
import com.sparta.viewfinder.profile.dto.ProfileAllResponseDto;
import com.sparta.viewfinder.profile.dto.ProfileDetailResponseDto;
import com.sparta.viewfinder.profile.dto.ProfileUpdateRequestDto;
import com.sparta.viewfinder.profile.dto.ProfileUpdateResponseDto;
import com.sparta.viewfinder.user.UserRepository;
import com.sparta.viewfinder.security.UserDetailsImpl;
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
        Profile profile = profileRepository.findById(profileId).orElseThrow(
                ()-> new NotFoundException(UserErrorCode.USER_NOT_FOUND));
        return new ProfileDetailResponseDto(profile);
    }

    @Transactional
    public ProfileUpdateResponseDto updateProfile(UserDetailsImpl userDetails, ProfileUpdateRequestDto profileUpdateRequestDto) {
        User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(
                ()-> new NotFoundException(UserErrorCode.USER_NOT_FOUND)
        );
        Profile profile = profileRepository.findByUserId(user.getId()).orElseThrow(
                ()-> new NotFoundException(UserErrorCode.USER_NOT_MATCH)
        );

        profile.update(profileUpdateRequestDto);
        return new ProfileUpdateResponseDto(profile);
    }
}
