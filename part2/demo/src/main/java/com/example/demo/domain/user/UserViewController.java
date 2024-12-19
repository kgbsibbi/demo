package com.example.demo.domain.user;

import com.example.demo.domain.user.dto.RequestAddUser;
import com.example.demo.domain.user.dto.ResponseAddUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UserViewController {
    private final UserService userService;

    @GetMapping
    public String users(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @PostMapping
    public String addUser(
            @RequestParam String name,
            @RequestParam String username,
            @RequestParam String password
    ) {
        RequestAddUser user =  new RequestAddUser();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        ResponseAddUser responseAddUser = userService.addUser(user);
        if(responseAddUser != null) { return "redirect:/users"; }
        else return "redirect:/users?message=err";
    }
}
