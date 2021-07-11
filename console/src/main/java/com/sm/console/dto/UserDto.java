package com.sm.console.dto;

import com.sm.console.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Integer id;
    private String loginId;
    private String password;

    public User toEntity() {
        return User.builder()
                .id(id)
                .loginId(loginId)
                .password(password)
                .build();
    }

    @Builder
    public UserDto(Integer id, String loginId, String password) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
    }
}
