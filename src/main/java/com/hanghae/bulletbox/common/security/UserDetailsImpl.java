package com.hanghae.bulletbox.common.security;

import com.hanghae.bulletbox.member.entity.Member;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final Member member; // 인증 완료된 User 객체
    private final String email; // 인증 완료된 User email
    private final String password; // 인증 완료된 User password

    public UserDetailsImpl(Member member, String email, String password) {
        this.member = member;
        this.email = email;
        this.password = password;
    }

    // 인증 완료된 User 를 가져오는 Getter
    public Member getMember() {
        return member;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}