package com.example.demo.domain.record.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import com.example.demo.domain.record.Record;
@Getter
@Setter
public class ResponseAddRecord {
    private Long id;
    private LocalDateTime createdTime;
    private String username;

    public ResponseAddRecord(Record record) {
        this.id = record.getId();
        this.createdTime = record.getCreatedTime();
        this.username = record.getUser().getUsername();
    }
}
