package com.example.demo.api;

import com.example.demo.domain.user.UserDto;
import com.example.demo.domain.user.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserServiceApi {

    private final UserService userService;

    @PostMapping("/register")
    public Long register(@RequestBody UserDto userDto){
        return userService.register(userDto);
    }

    @PostMapping("/login")
    public Long login(@RequestBody UserDto userDto){
        return userService.login(userDto);
    }

    @PostMapping("/changeConfig/{id}")
    public Long changeConfig(@PathVariable Long id,
                             @RequestBody UserDto userDto){
        return userService.changeConfig(id, userDto);
    }

    @PostMapping("/withdraw/{id}")
    public Long withdraw(@PathVariable Long id,
                         @RequestBody UserDto userDto){
        return userService.withdraw(id, userDto);
    }
}
