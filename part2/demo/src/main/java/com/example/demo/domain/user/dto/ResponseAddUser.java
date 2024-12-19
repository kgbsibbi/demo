package com.example.demo.domain.user.dto;

import com.example.demo.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseAddUser {
    private Long id;
    private String username;

    public ResponseAddUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
