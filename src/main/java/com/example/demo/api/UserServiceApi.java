package com.example.demo.api;

import com.example.demo.domain.user.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserServiceApi {

    private final UserService userService;

    @PostMapping("/register")
    public Long register(@RequestParam UserDto userDto){

        System.out.println(userDto.getName());
        System.out.println(userDto.getPassword());

        return userService.register(userDto);
    }

    @PostMapping("/login")
    public Long login(UserDto userDto){
        return userService.login(userDto);
    }

    @PostMapping("/changeConfig/{id}")
    public Long changeConfig(@PathVariable Long id,
                             UserDto userDto){
        return userService.changeConfig(id, userDto);
    }

    @PostMapping("/withdraw/{id}")
    public Long withdraw(@PathVariable Long id,
                         UserDto userDto){
        return userService.withdraw(id, userDto);
    }
}
