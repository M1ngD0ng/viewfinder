package com.sparta.viewfinder.follow.repository;

import com.sparta.viewfinder.follow.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long>, FollowRepositoryQuery{
    Optional<Follow> findByFollower_IdAndFollowee_Id(Long followerId, Long followeeId);
}
