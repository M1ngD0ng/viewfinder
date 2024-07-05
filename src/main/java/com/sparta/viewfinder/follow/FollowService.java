package com.sparta.viewfinder.follow;

import com.sparta.viewfinder.exception.errorcode.FollowErrorCode;
import com.sparta.viewfinder.exception.errorcode.UserErrorCode;
import com.sparta.viewfinder.exception.exception.CommonException;
import com.sparta.viewfinder.exception.exception.NotFoundException;
import com.sparta.viewfinder.follow.repository.FollowRepository;
import com.sparta.viewfinder.post.Post;
import com.sparta.viewfinder.post.dto.PostResponseDto;
import com.sparta.viewfinder.user.User;
import com.sparta.viewfinder.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {
    private static final String FOLLOW_SUCCESS = "팔로우가 성공적으로 수행되었습니다.";
    private static final String UNFOLLOW_SUCCESS = "팔로우 취소가 성공적으로 수행되었습니다.";
    private static final int PAGE_SIZE = 5;

    private final UserRepository userRepository;
    private final FollowRepository followRepository;


    public String followToggle(Long userId, // 팔로우/언팔로우 하고 싶은 상대의 userId
                               User user) {
        User targetUser = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(UserErrorCode.USER_NOT_FOUND)
        );
        if (targetUser.getId().equals(user.getId())) {
            throw new CommonException(FollowErrorCode.SELF_FOLLOW_ERROR);
        }

        Optional<Follow> isFollowed = followRepository.findByFollower_IdAndFollowee_Id(user.getId(), targetUser.getId());
        if (isFollowed.isPresent()) {
            followRepository.delete(isFollowed.get());
            return UNFOLLOW_SUCCESS;
        } else {
            Follow newFollow = new Follow(user, targetUser);
            followRepository.save(newFollow);
            return FOLLOW_SUCCESS;
        }
    }

    public Page<PostResponseDto> getFollowedPost(int page, String sortBy, User user) {
        Sort.Direction direction = Sort.Direction.DESC;
        if(sortBy.equals("username")) sortBy="user.name";
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, sort);

        Page<Post> postList = followRepository.searchFollowedPost(user, sortBy, pageable);

        return postList.map(PostResponseDto::new);
    }
}
