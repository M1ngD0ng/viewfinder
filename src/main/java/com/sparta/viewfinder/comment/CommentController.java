package com.sparta.viewfinder.comment;

import com.sparta.viewfinder.comment.dto.CommentRequestDto;
import com.sparta.viewfinder.comment.dto.CommentResponseDto;
import com.sparta.viewfinder.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class CommentController {

    private final CommentService commentService;
    private static final String DELETE_COMMENT_SUCCESS_MESSAGE = "댓글이 삭제되었습니다";
    private static final String NOT_FOUND_COMMENT = "댓글을 찾을 수 없습니다";

    // 댓글 생성
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable("postId") Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CommentRequestDto requestDto) {
        CommentResponseDto res = commentService.createComment(userDetails, postId, requestDto);
        return ResponseEntity.ok(res);
    }

    // 게시물의 전체 댓글 조회
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<Object> readCommentAll(@PathVariable("postId") Long postId) {
        List<CommentResponseDto> res = commentService.readCommentAll(postId);
        if (res.isEmpty()) {
            return ResponseEntity.ok().body(NOT_FOUND_COMMENT);
        } else {
            return ResponseEntity.ok(res);
        }
    }

    // 게시물의 특정 댓글 조회
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> readComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId) {
        return ResponseEntity.ok(commentService.readComment(postId, commentId));
    }

    // 댓글 수정
    @PatchMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CommentRequestDto requestDto) {
        CommentResponseDto res = commentService.updateComment(postId, userDetails, commentId, requestDto);
        return ResponseEntity.ok(res);
    }

    // 댓글 삭제
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(postId, commentId, userDetails);
        return ResponseEntity.ok().body(DELETE_COMMENT_SUCCESS_MESSAGE);
    }
}