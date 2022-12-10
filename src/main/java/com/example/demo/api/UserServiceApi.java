package com.example.demo.api;

import com.example.demo.api.dto.UserDto;
import com.example.demo.domain.user.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserServiceApi {

    private final UserService userService;

    @GetMapping
    public String apiTest(){
        return "get 요청";
    }

    @PostMapping
    public List<User> register(@RequestBody UserDto userDto) {

        userService.register(userDto);

        return userService.findAllUser();
    }

    @PostMapping("/login")
    public List<User> login(@RequestBody UserDto userDto) {

        userService.login(userDto);

        return userService.findAllUser();
    }

    @PutMapping("/{id}")
    public Long changeConfig(@PathVariable Long id,
                             @RequestBody UserDto userDto) {
        return userService.changeConfig(id, userDto);
    }

    @DeleteMapping("/{id}")
    public Long withdraw(@PathVariable Long id,
                         @RequestBody UserDto userDto) {
        return userService.withdraw(id, userDto);
    }
}
