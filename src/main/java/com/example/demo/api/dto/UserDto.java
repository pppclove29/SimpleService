package com.example.demo.api.dto;


import com.example.demo.domain.user.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    String name;
    String password;
}
