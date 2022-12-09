package com.example.demo.service;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserDto;
import com.example.demo.domain.user.UserRepository;
import com.example.demo.except.WrongPasswordException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long register(UserDto userDto) {
        // 가입 유저의 중복은 Controller 에서 처리하며 중복된 요청은 들어오지 않는다고 가정한다.

        User new_user = User.builder()
                .name(userDto.getName())
                .password(userDto.getPassword())
                .build();

        return userRepository.save(new_user).getId();
    }

    @Transactional
    public Long login(UserDto userDto) throws WrongPasswordException {
            User user = userRepository.findByName(userDto.getName());
        user.checkPassword(userDto.getPassword());

        System.out.println("로그인 성공");
        return user.getId();
    }

    @Transactional
    public Long changeConfig(Long id, UserDto new_userDto){
        User user = userRepository.getReferenceById(id);

        user.changeConfig(
                new_userDto.getName(),
                new_userDto.getPassword()
        );

        return user.getId();
    }


    @Transactional
    public Long withdraw(Long id, UserDto userDto){
        userRepository.deleteById(id);
        // 연관된 게시물도 지워지나? 안지워질듯?

        return id;
    }
}
