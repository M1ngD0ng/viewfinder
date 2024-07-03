package com.sparta.viewfinder.post;

import com.sparta.viewfinder.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
