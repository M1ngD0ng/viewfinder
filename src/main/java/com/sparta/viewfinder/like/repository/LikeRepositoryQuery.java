package com.sparta.viewfinder.like.repository;

import com.sparta.viewfinder.post.Post;
import com.sparta.viewfinder.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LikeRepositoryQuery {
    Page<Post> searchLikedPost(User user, Pageable pageable);
}
