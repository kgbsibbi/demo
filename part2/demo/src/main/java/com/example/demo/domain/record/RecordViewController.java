package com.example.demo.domain.record;

import com.example.demo.domain.record.dto.RequestAddRecord;
import com.example.demo.domain.record.dto.ResponseAddRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Controller
public class RecordViewController {
    private final RecordService recordService;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/records")
    public String records(
            Model model,
            @RequestParam(value = "date", defaultValue = "") String date) {
        System.out.println(date);
        if(!date.isEmpty() && date.matches("(19|20)\\d{2}-(0[1-9]|1[0,1,2])-(0[1-9]|[12][0-9]|3[01])")){
            LocalDate time = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            model.addAttribute("date", date);
            model.addAttribute("records", recordService.getRecordsByDate(time));
        } else {
            model.addAttribute("records", recordService.getRecords());
        }
        return "records";
    }

    @PostMapping("/records")
    public String addRecord(
            @RequestParam String username,
            @RequestParam String password) {

        RequestAddRecord record = new RequestAddRecord();
        record.setUsername(username);
        record.setPassword(password);
        ResponseAddRecord result = recordService.addRecord(record);
        if(result != null) { return "redirect:/"; }
        else { return "redirect:/?message=error"; }
    }
}
