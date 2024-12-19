package com.example.demo.domain.record;

import com.example.demo.domain.record.dto.RequestAddRecord;
import com.example.demo.domain.record.dto.ResponseAddRecord;
import com.example.demo.domain.record.dto.ResponseGetRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/records")
public class RecordApiController {
    private final RecordService recordService;

    @GetMapping
    public ResponseEntity<List<ResponseGetRecord>> findAll(
            @RequestParam(value = "date", defaultValue = "") String date
    ) {
        if(!date.isEmpty() && date.matches("(19|20)\\d{2}-(0[1-9]|1[0,1,2])-(0[1-9]|[12][0-9]|3[01])")) {
            LocalDate time = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return ResponseEntity.ok(recordService.getRecordsByDate(time));
        } else
            return ResponseEntity.ok(recordService.getRecords());
    }

    @PostMapping
    public ResponseEntity<ResponseAddRecord> addRecord(@RequestBody RequestAddRecord record) {
        ResponseAddRecord responseAddRecord = recordService.addRecord(record);
        if (responseAddRecord != null) {
            return ResponseEntity.status(201).body(responseAddRecord);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
