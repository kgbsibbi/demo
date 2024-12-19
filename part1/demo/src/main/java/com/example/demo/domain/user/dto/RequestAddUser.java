package com.example.demo.domain.user.dto;

import com.example.demo.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestAddUser {
    private String name;
    private String username;
    private String password;

    public static User toEntity(RequestAddUser requestAddUser) {
        return User.builder()
                .name(requestAddUser.getName())
                .username(requestAddUser.getUsername())
                .password(requestAddUser.getPassword())
                .build();
    }
}
