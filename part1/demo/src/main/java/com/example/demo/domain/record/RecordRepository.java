package com.example.demo.domain.record;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findAllByOrderByCreatedTimeDesc();
    List<Record> findAllByCreatedTimeBetween(LocalDateTime createdTimeAfter, LocalDateTime createdTimeBefore, Sort sort);
}
