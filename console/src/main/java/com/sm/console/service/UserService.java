package com.sm.console.service;

import com.sm.console.dto.UserDto;
import com.sm.console.entity.User;
import com.sm.console.enums.RoleEnum;
import com.sm.console.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    @Transactional
    public Integer createUser(UserDto userDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(userDto.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userWrapper = userRepository.findByLoginId(username);
        User user = userWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (username.equals("admin")) {
            authorities.add(new SimpleGrantedAuthority(RoleEnum.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(RoleEnum.USER.getValue()));
        }

        return new org.springframework.security.core.userdetails.User(user.getLoginId(), user.getPassword(), authorities);
    }
}
