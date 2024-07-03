package com.sparta.viewfinder.like;

import com.sparta.viewfinder.entity.comment.Comment;
import com.sparta.viewfinder.constant.ContentsTypeEnum;
import com.sparta.viewfinder.entity.like.Like;
import com.sparta.viewfinder.entity.post.Post;
import com.sparta.viewfinder.entity.user.User;
import com.sparta.viewfinder.exception.errorcode.CommonErrorCode;
import com.sparta.viewfinder.exception.errorcode.LikeErrorCode;
import com.sparta.viewfinder.exception.errorcode.PostErrorCode;
import com.sparta.viewfinder.exception.exception.CommonException;
import com.sparta.viewfinder.exception.exception.NotFoundException;
import com.sparta.viewfinder.comment.CommentRepository;
import com.sparta.viewfinder.post.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    private static final String CREATE_LIKE = "좋아요가 등록되었습니다.";
    private static final String CANCEL_LIKE = "좋아요가 취소되었습니다.";

    @Transactional
    public LikeResponseDto postLikeToggle(Long contentId, User user) {
        LikeResponseDto responseDto;

        Post post = postRepository.findById(contentId).orElseThrow(
                () -> new NotFoundException(PostErrorCode.POST_NOT_FOUND)
        );

        if(post.getUser().getId().equals(user.getId())){
            throw new CommonException(LikeErrorCode.SELF_LIKE_ERROR);
        }

        Optional<Like> isLiked = likeRepository.findByUserIdAndContentIdAndContentsType(user.getId(), contentId, ContentsTypeEnum.POST);
        if (isLiked.isPresent()) {
            post.setLikeCount(post.getLikeCount() - 1);
            postRepository.save(post);

            likeRepository.delete(isLiked.get());
            responseDto = new LikeResponseDto(ContentsTypeEnum.POST,
                    contentId,
                    post.getLikeCount(),
                    CANCEL_LIKE);
        } else {
            Like newLike = new Like(user, ContentsTypeEnum.POST, contentId);
            likeRepository.save(newLike);

            post.setLikeCount(post.getLikeCount() + 1);
            postRepository.save(post);
            responseDto = new LikeResponseDto(ContentsTypeEnum.POST,
                    contentId,
                    post.getLikeCount(),
                    CREATE_LIKE);
        }
        return responseDto;
    }

    public LikeResponseDto commentLikeToggle(Long contentId, User user) {
        LikeResponseDto responseDto;

        Comment comment = commentRepository.findById(contentId).orElseThrow(
                ()-> new NotFoundException(CommonErrorCode.RESOURCE_NOT_FOUND)
        );
        if(comment.getUser().getId().equals(user.getId())){
            throw new CommonException(LikeErrorCode.SELF_LIKE_ERROR);
        }
        Optional<Like> isLiked = likeRepository.findByUserIdAndContentIdAndContentsType(user.getId(), contentId, ContentsTypeEnum.COMMENT);
        if (isLiked.isPresent()) {
            comment.setLikeCount(comment.getLikeCount() - 1);
            commentRepository.save(comment);

            likeRepository.delete(isLiked.get());
            responseDto = new LikeResponseDto(ContentsTypeEnum.COMMENT,
                    contentId,
                    comment.getLikeCount(),
                    CANCEL_LIKE);
        } else {
            Like newLike = new Like(user, ContentsTypeEnum.COMMENT, contentId);
            likeRepository.save(newLike);

            comment.setLikeCount(comment.getLikeCount() + 1);
            commentRepository.save(comment);
            responseDto = new LikeResponseDto(ContentsTypeEnum.COMMENT,
                    contentId,
                    comment.getLikeCount(),
                    CREATE_LIKE);
        }
        return responseDto;
    }
}
