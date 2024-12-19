package com.example.demo.domain.user;

import com.example.demo.domain.user.dto.RequestAddUser;
import com.example.demo.domain.user.dto.ResponseAddUser;
import com.example.demo.domain.user.dto.ResponseGetUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserApiController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<ResponseGetUser>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<ResponseAddUser> addUser(@RequestBody RequestAddUser requestAddUser) {
        ResponseAddUser responseAddUser = userService.addUser(requestAddUser);
        if(responseAddUser != null) {
            return ResponseEntity.status(201).body(responseAddUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        Long result = userService.removeUser(id);
        System.out.println(result);
        if(result != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
