package com.example.piper.piperchat.service;

//import com.example.piper.piperchat.model.User;
import com.example.piper.piperchat.repository.UserRepository;
import com.example.piper.piperchat.security.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.example.piper.piperchat.model.User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        CustomUserDetail customUserDetail=new CustomUserDetail();
        customUserDetail.setUser(user);
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        customUserDetail.setAuthorities(authorities);

        return customUserDetail;
    }
}
