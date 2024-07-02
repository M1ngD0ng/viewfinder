package com.sparta.viewfinder.dto.comment;

import com.sparta.viewfinder.entity.comment.Comment;
import lombok.Getter;

@Getter

public class CommentResponseDto {
    private Long userId;
    private String username;
    private Long postId;
    private String content;
    private String createAt;
    private String modifiedAt;
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