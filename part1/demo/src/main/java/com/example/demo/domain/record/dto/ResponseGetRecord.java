package com.example.demo.domain.record.dto;

import com.example.demo.domain.record.Record;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ResponseGetRecord {
    private Long id;
    private LocalDateTime createdTime;
    private String username;
    private String name;

    public ResponseGetRecord(Record record ) {
        this.id = record.getId();
        this.createdTime = record.getCreatedTime();
        this.name = record.getUser().getName();
        this.username = record.getUser().getUsername();
    }
}
