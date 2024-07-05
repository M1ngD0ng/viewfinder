package com.sparta.viewfinder.follow.repository;

import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.viewfinder.follow.QFollow;
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
public class FollowRepositoryQueryImpl implements FollowRepositoryQuery{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Post> searchFollowedPost(User user, Pageable pageable) {
        QPost post = QPost.post;
        QFollow follow = QFollow.follow;

        List<Post> postList = jpaQueryFactory.select(post)
                .from(follow)
                .join(post).on(follow.followee.id.eq(post.user.id))
                .where(
                        follow.follower.id.eq(user.getId())
                )
                .orderBy(post.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long totalSize = countFollowedPost(user);
        return new PageImpl<>(postList, pageable, totalSize);
    }

    @Override
    public Long countFollowedPost(User user) {
        QPost post = QPost.post;
        QFollow follow = QFollow.follow;

        return jpaQueryFactory.select(Wildcard.count)
                .from(follow)
                .join(post).on(follow.followee.id.eq(post.user.id))
                .where(
                        follow.follower.id.eq(user.getId())
                )
                .fetch().get(0);
    }
}
