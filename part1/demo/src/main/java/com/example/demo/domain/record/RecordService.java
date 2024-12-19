package com.example.demo.domain.record;

import com.example.demo.domain.record.dto.RequestAddRecord;
import com.example.demo.domain.record.dto.ResponseAddRecord;
import com.example.demo.domain.record.dto.ResponseGetRecord;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRepository;
import com.example.demo.domain.user.dto.ResponseGetUser;
import lombok.RequiredArgsConstructor;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecordService {

    private final RecordRepository recordRepository;
    private final UserRepository userRepository;

    public List<ResponseGetRecord> getRecords(){
        return recordRepository.findAllByOrderByCreatedTimeDesc()
                .stream()
                .map(ResponseGetRecord::new).collect(Collectors.toList());
    }

    public List<ResponseGetRecord> getRecordsByDate(LocalDateTime time){
        LocalDateTime startTime = LocalDateTime.of(time.getYear(), time.getMonth(), time.getDayOfMonth(), 0, 0, 0);
        LocalDateTime endTimd = LocalDateTime.of(time.getYear(), time.getMonth(), time.getDayOfMonth(), 23, 59, 59);
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");

        return recordRepository.findAllByCreatedTimeBetween(startTime, endTimd, sort)
                .stream()
                .map(ResponseGetRecord::new)
                .collect(Collectors.toList());
    }

    public ResponseAddRecord addRecord(RequestAddRecord request){
        User user = userRepository.findByUsername(request.getUsername()).orElse(null);
        if(user == null){ return null; }
        if(!user.getPassword().equals(request.getPassword())){return null;}
        Record savedRecord = recordRepository.save(Record.builder().user(user).build());
        return new ResponseAddRecord(savedRecord);
    }
}
