package com.sparta.viewfinder.like;

import com.sparta.viewfinder.constant.ContentsTypeEnum;
import com.sparta.viewfinder.constant.Timestamped;
import com.sparta.viewfinder.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "liked")
@NoArgsConstructor
public class Like extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private ContentsTypeEnum contentsType;

    @Column
    private Long contentId;

    public Like(User user, ContentsTypeEnum contentsType, Long contentId) {
        this.user = user;
        this.contentsType = contentsType;
        this.contentId = contentId;
    }
}
