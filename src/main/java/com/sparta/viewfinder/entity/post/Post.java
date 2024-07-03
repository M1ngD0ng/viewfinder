package com.sparta.viewfinder.entity.post;

import com.sparta.viewfinder.post.dto.PostRequestDto;
import com.sparta.viewfinder.constant.Timestamped;
import com.sparta.viewfinder.entity.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "post")
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private String content;

    @Column
    private Long likeCount;

    public Post(User user, String content) {
        this.user = user;
        this.content = content;
        this.likeCount = 0L;
    }

    public void update(PostRequestDto requestDto) {
        this.content = requestDto.getContent();
    }

}
