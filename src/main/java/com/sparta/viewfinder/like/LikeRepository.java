package com.sparta.viewfinder.like;

import com.sparta.viewfinder.constant.ContentsTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserIdAndContentIdAndContentsType(Long userId, Long contentId, ContentsTypeEnum contentsType);
}
