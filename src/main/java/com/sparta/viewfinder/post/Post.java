package com.sparta.viewfinder.post;

import com.sparta.viewfinder.constant.Timestamped;
import com.sparta.viewfinder.post.dto.PostRequestDto;
import com.sparta.viewfinder.user.User;
import jakarta.persistence.*;
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
