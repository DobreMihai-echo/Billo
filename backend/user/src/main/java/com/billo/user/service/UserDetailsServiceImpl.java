package com.billo.user.service;

import com.billo.user.model.AppRole;
import com.billo.user.model.AppUser;
import com.billo.user.model.ERole;
import com.billo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException(String.format("User with username %s, not found",username)));
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        boolean enabled = false;
        if (!user.getRoles().stream().filter(role -> role.getName().equals(ERole.ROLE_ADMIN)).collect(Collectors.toList()).isEmpty()) {
            enabled=true;
        } else {
            enabled  = user.isAccountVerified();
        }
        return
                //User.withUsername(user.getUsername()).password(user.getPassword()).disabled(enabled).authorities(authorities).build();

                    UserDetailsImpl
                    .builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .password(user.getPassword())
                .authorities(authorities)
                .isAccountNonLocked(true)
                .isEnabled(enabled)
                .isCredentialsNonExpired(true)
                .isAccountNonExpired(true)
                .build();
    }

    public int enableAppUser(String username) {
        try {
            return userRepository.enableAppUser(username);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
