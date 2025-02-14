package com.sparta.viewfinder.security;

import com.sparta.viewfinder.constant.UserStatusEnum;
import java.util.ArrayList;
import java.util.Collection;

import com.sparta.viewfinder.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

  private final User user;

  public UserDetailsImpl(User user) {
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  //사용자 권한 정하는 메서드
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    UserStatusEnum statusEnum = user.getStatus();
    String status = statusEnum.getStatus();
// 없어도 되는 건지
//    UserRoleEnum userRoleEnum = user.getUserRole();
//    String role = userRoleEnum.getAuthority();

    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(status);
//    SimpleGrantedAuthority simpleGrantedAuthority1 = new SimpleGrantedAuthority(role);

    Collection<GrantedAuthority> authorities = new ArrayList<>();

    authorities.add(simpleGrantedAuthority);
//    authorities.add(simpleGrantedAuthority1);

    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
