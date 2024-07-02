package com.sparta.viewfinder.repository;

import com.sparta.viewfinder.entity.constant.ContentsTypeEnum;
import com.sparta.viewfinder.entity.like.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserIdAndContentIdAndContentsType(Long userId, Long contentId, ContentsTypeEnum contentsType);
}
