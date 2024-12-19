package com.example.demo.domain.record.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestAddRecord {
    private String username;
    private String password;
}
