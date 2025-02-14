package com.sparta.viewfinder.post.dto;

import com.sparta.viewfinder.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long userId;
    private String name;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long likeCount;

    public PostResponseDto(Post post) {
        this.userId = post.getUser().getId();
        this.name = post.getUser().getName();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.likeCount = post.getLikeCount();
    }
}
