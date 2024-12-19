package com.example.demo.domain.user;

import com.example.demo.domain.user.dto.RequestAddUser;
import com.example.demo.domain.user.dto.ResponseAddUser;
import com.example.demo.domain.user.dto.ResponseGetUser;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<ResponseGetUser> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(ResponseGetUser::new).collect(Collectors.toList());
    }

    public ResponseAddUser addUser(RequestAddUser request) {
        try {
            User savedUser = userRepository.save(RequestAddUser.toEntity(request));
            return new ResponseAddUser(savedUser);
        }catch(DataIntegrityViolationException e){ // username 중복 등
            return null;
        }
    }

    public Long removeUser(Long userId) {
        try {
            userRepository.deleteById(userId);
            return userId;
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }
}
