package com.example.piper.piperchat.security;

import com.example.piper.piperchat.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class CustomUserDetail implements UserDetails {

//    private static final long serialVersionUID = 1L;
    private User user;

    Set<GrantedAuthority> authorities=null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities)
    {
        this.authorities=authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    public boolean isAccountNonExpired() {
//        return user.isAccountNonExpired();
        return true;
    }

    public boolean isAccountNonLocked() {
//        return user.isAccountNonLocked();
        return true;
    }

    public boolean isCredentialsNonExpired() {
//        return user.isCredentialsNonExpired();
        return true;
    }

    public boolean isEnabled() {
//        return user.isAccountEnabled();
        return true;
    }

}
