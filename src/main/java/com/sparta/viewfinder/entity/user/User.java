package com.sparta.viewfinder.entity.user;

import com.sparta.viewfinder.entity.constant.Timestamped;
import com.sparta.viewfinder.entity.constant.UserRoleEnum;
import com.sparta.viewfinder.entity.constant.UserStatusEnum;
import com.sparta.viewfinder.entity.profile.Profile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "user")
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String name;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private UserStatusEnum status;

    private String refreshToken;

    private String statusUpdate;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum userRole;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Profile profile;

    public User(String username, String password, String name, String email, UserRoleEnum userRole) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.status = UserStatusEnum.USER;
        this.userRole = userRole;
        this.profile = new Profile(this);
    }

    public void withDraw() {
        this.status = UserStatusEnum.NON_USER;
        this.statusUpdate = this.getModifiedAt();
        this.refreshToken = null;
    }


    public boolean logout(){
        refreshToken = null;
        return refreshToken == null ? true : false;
    }

    public void saveRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
