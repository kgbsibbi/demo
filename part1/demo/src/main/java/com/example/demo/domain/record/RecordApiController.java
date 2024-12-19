package com.example.demo.domain.record;

import com.example.demo.domain.record.dto.RequestAddRecord;
import com.example.demo.domain.record.dto.ResponseAddRecord;
import com.example.demo.domain.record.dto.ResponseGetRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
        if(date.isEmpty()) {
            return ResponseEntity.ok(recordService.getRecords());
        } else {
            try {
                int year = Integer.parseInt(date.substring(0, 4));
                int month = Integer.parseInt(date.substring(4, 6));
                int day = Integer.parseInt(date.substring(6, 8));
                LocalDateTime time = LocalDateTime.of(year, month, day, 0, 0, 0);

                return ResponseEntity.ok(recordService.getRecordsByDate(time));
            } catch (NumberFormatException e) {
                return ResponseEntity.ok(recordService.getRecords());
            }
        }
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
