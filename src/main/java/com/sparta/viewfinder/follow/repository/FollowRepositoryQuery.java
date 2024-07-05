package com.sparta.viewfinder.follow.repository;

import com.sparta.viewfinder.post.Post;
import com.sparta.viewfinder.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FollowRepositoryQuery {
    Page<Post> searchFollowedPost(User user, String sortBy, Pageable pageable);
    Long countFollowedPost(User user);
}
