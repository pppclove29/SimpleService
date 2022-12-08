package com.example.demo.domain.user;


import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDto {
   String name;
   String password;

   @Builder
    public UserDto(String name, String password){
       this.name=name;
       this.password=password;
   }
}
