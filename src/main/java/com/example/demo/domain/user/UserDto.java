package com.example.demo.domain.user;


import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDto {
   String name;
   String password;
   Role role;

   @Builder
    public UserDto(String name, String password, Role role){
       this.name=name;
       this.password=password;
       this.role=role;
   }
}
