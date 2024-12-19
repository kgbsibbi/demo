package com.example.demo.domain.record;

import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="records")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdTime;

    @ManyToOne
    private User user;

    @Builder
    public Record(User user) {
        this.user = user;
        this.createdTime = LocalDateTime.now();
    }
}
