package com.sparta.viewfinder.repository;

import com.sparta.viewfinder.entity.ContentsTypeEnum;
import com.sparta.viewfinder.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserIdAndContentIdAndContentsType(Long userId, Long contentId, ContentsTypeEnum contentsType);
}
