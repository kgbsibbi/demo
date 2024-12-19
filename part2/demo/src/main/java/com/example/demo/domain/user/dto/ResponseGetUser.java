package com.example.demo.domain.user.dto;

import com.example.demo.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseGetUser {
    private Long id;
    private String name;
    private String username;

    public ResponseGetUser(User user) {
        id = user.getId();
        name = user.getName();
        username = user.getUsername();
    }
}
