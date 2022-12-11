package com.example.demo.api;

import com.example.demo.api.dto.UserDto;
import com.example.demo.domain.user.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserServiceApi {

    private final UserService userService;

    @PostMapping
    public List<UserDto> register(@RequestBody UserDto userDto) {

        userService.register(userDto);

        return userService.findAllUser();
    }

    @PostMapping("/login")
    public List<UserDto> login(@RequestBody UserDto userDto) {

        userService.login(userDto);

        return userService.findAllUser();
    }

    @PostMapping("/{id}")
    public List<UserDto> changeConfig(@RequestBody UserDto userDto,
                                      @PathVariable Long id) {

        userService.changeConfig(id, userDto);

        return userService.findAllUser();
    }

    @DeleteMapping("/{id}")
    public List<UserDto> withdraw(@PathVariable Long id,
                                  @RequestBody UserDto userDto) {
        userService.withdraw(id, userDto);

        return userService.findAllUser();
    }
}
