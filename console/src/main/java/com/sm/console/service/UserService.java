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

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    @Transactional
    public boolean createUser(UserDto userDto) {
        User user = userRepository.findByLoginId(userDto.getLoginId());

        if (user == null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userRepository.save(userDto.toEntity());

            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(username);

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (username.equals("admin")) {
            authorities.add(new SimpleGrantedAuthority(RoleEnum.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(RoleEnum.USER.getValue()));
        }

        return new org.springframework.security.core.userdetails.User(user.getLoginId(), user.getPassword(), authorities);
    }
}
