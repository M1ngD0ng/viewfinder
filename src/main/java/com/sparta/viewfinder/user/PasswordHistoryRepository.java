package com.sparta.viewfinder.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasswordHistoryRepository extends JpaRepository<PasswordHistory, Long> {
    List<PasswordHistory> findTop3ByUserIdOrderByCreatedAtDesc(Long userId);
}
