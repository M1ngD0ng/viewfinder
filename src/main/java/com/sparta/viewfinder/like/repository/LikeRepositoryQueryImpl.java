package com.sparta.viewfinder.like.repository;

import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.viewfinder.comment.Comment;
import com.sparta.viewfinder.comment.QComment;
import com.sparta.viewfinder.constant.ContentsTypeEnum;
import com.sparta.viewfinder.like.QLike;
import com.sparta.viewfinder.post.Post;
import com.sparta.viewfinder.post.QPost;
import com.sparta.viewfinder.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryQueryImpl implements LikeRepositoryQuery {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Post> searchLikedPost(User user, Pageable pageable) {
        QPost post = QPost.post;
        QLike like = QLike.like;

        List<Post> postList = jpaQueryFactory.select(post)
                .from(like)
                .join(post).on(like.contentId.eq(post.id))
                .where(
                        like.user.id.eq(user.getId())
                                .and(like.contentsType.eq(ContentsTypeEnum.POST))
                )
                .orderBy(post.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalSize = countLikedPost(user);
        return new PageImpl<>(postList, pageable, totalSize);
    }

    @Override
    public Page<Comment> searchLikedComment(User user, Pageable pageable) {
        QComment comment = QComment.comment;
        QLike like = QLike.like;

        List<Comment> postList = jpaQueryFactory.select(comment)
                .from(like)
                .join(comment).on(like.contentId.eq(comment.id))
                .where(
                        like.user.id.eq(user.getId())
                                .and(like.contentsType.eq(ContentsTypeEnum.COMMENT))
                )
                .orderBy(comment.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalSize = countLikedComment(user);
        return new PageImpl<>(postList, pageable, totalSize);
    }
    @Override
    public Long countLikedPost(User user){
        QPost post = QPost.post;
        QLike like = QLike.like;

        return jpaQueryFactory.select(Wildcard.count)
                .from(like)
                .join(post).on(like.contentId.eq(post.id))
                .where(
                        like.user.id.eq(user.getId())
                                .and(like.contentsType.eq(ContentsTypeEnum.POST))
                )
                .fetch().get(0);
    }

    @Override
    public Long countLikedComment(User user){
        QComment comment = QComment.comment;
        QLike like = QLike.like;

        return jpaQueryFactory.select(Wildcard.count)
                .from(like)
                .join(comment).on(like.contentId.eq(comment.id))
                .where(
                        like.user.id.eq(user.getId())
                                .and(like.contentsType.eq(ContentsTypeEnum.COMMENT))
                )
                .fetch().get(0);
    }
}

