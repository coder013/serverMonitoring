package com.sm.console.dto;

import com.sm.console.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer id;
    @NotBlank(message = "ID를 입력하십시오.")
    private String loginId;
    @NotBlank(message = "비밀번호를 입력하십시오.")
    private String password;

    public User toEntity() {
        return User.builder()
                .id(id)
                .loginId(loginId)
                .password(password)
                .build();
    }
}
