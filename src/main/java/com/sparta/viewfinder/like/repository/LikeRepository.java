package com.sparta.viewfinder.like.repository;

import com.sparta.viewfinder.constant.ContentsTypeEnum;
import com.sparta.viewfinder.like.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long>, LikeRepositoryQuery{
    Optional<Like> findByUserIdAndContentIdAndContentsType(Long userId, Long contentId, ContentsTypeEnum contentsType);
}
