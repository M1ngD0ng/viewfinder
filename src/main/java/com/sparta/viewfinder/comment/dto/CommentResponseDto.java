package com.sparta.viewfinder.comment.dto;

import com.sparta.viewfinder.comment.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class CommentResponseDto {
    private Long userId;
    private String username;
    private Long postId;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private Long likeCount;


    public CommentResponseDto(Comment comment){
        this.userId = comment.getUser().getId();
        this.username = comment.getUser().getUsername();
        this.postId = comment.getPost().getId();
        this.content = comment.getContent();
        this.createAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.likeCount = comment.getLikeCount();
    }
}