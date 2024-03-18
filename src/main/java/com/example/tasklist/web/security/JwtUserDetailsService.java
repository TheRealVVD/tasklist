package com.example.tasklist.web.security;

import com.example.tasklist.model.user.User;
import com.example.tasklist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);

        if (null == user) {
            throw new UsernameNotFoundException("Username not found");
        }
//        user.g
        return JwtEntityFactory.create(user);
    }
}

