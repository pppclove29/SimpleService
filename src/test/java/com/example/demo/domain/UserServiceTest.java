package com.example.demo.domain;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserDto;
import com.example.demo.domain.user.UserRepository;
import com.example.demo.except.WrongPasswordException;
import com.example.demo.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @BeforeEach
    void clearDB() {
        userRepository.deleteAll();
    }

    @Test
    public void 회원가입테스트() {
        String testName = "테스트 이름";
        String testPassword = "테스트 비밀번호";

        UserDto dto = UserDto.builder()
                .name(testName)
                .password(testPassword)
                .build();

        Long id = userService.register(dto);

        User user = userRepository.getReferenceById(id);
        User user2 = userRepository.findById(id).get();

        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getName()).isEqualTo(testName);
        assertThat(user.getPassword()).isEqualTo(testPassword);

        assertThat(user).isEqualTo(user2);
    }

    @Test
    public void 로그인테스트() {
        String testName = "테스트 이름";
        String testPassword = "테스트 비밀번호";

        UserDto register_dto = UserDto.builder()
                .name(testName)
                .password(testPassword)
                .build();

        Long r_id = userService.register(register_dto);

        UserDto login_dto = UserDto.builder()
                .name(testName)
                .password(testPassword)
                .build();

        Long l_id = userService.login(login_dto);

        User user = userRepository.getReferenceById(l_id);

        assertThat(r_id).isEqualTo(l_id);
        assertThat(user.getName()).isEqualTo(testName);
        assertThat(user.getPassword()).isEqualTo(testPassword);
    }

    @Test
    public void 틀린비밀번호로그인테스트() {
        String testName = "테스트 이름";
        String testPassword = "테스트 비밀번호";

        UserDto register_dto = UserDto.builder()
                .name(testName)
                .password(testPassword)
                .build();

        Long r_id = userService.register(register_dto);

        UserDto login_dto = UserDto.builder()
                .name(testName)
                .password(testPassword + "Wrong")
                .build();
        try {
            Long l_id = userService.login(login_dto);
        } catch (WrongPasswordException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void 회원정보변경테스트() {
        String testName = "테스트 이름";
        String testPassword = "테스트 비밀번호";

        UserDto dto = UserDto.builder()
                .name(testName)
                .password(testPassword)
                .build();

        Long id = userService.register(dto);

        String newTestName = "새로운 테스트 이름";
        String newTestPassword = "새로운 테스트 비밀번호";

        UserDto new_dto = UserDto.builder()
                .name(newTestName)
                .password(newTestPassword)
                .build();

        Long new_id = userService.changeConfig(id, new_dto);

        User user = userRepository.getReferenceById(new_id);

        assertThat(user.getName()).isEqualTo(newTestName);
        assertThat(user.getPassword()).isEqualTo(newTestPassword);
        assertThat(user.getId()).isEqualTo(id);
    }

    @Test
    public void 회원탈퇴테스트() {
        String testName = "테스트 이름";
        String testPassword = "테스트 비밀번호";

        UserDto dto = UserDto.builder()
                .name(testName)
                .password(testPassword)
                .build();

        Long id = userService.register(dto);

        Long deleted_id = userService.withdraw(id,null);

        try{
            userRepository.findById(deleted_id).orElseThrow(
                    () -> new IllegalArgumentException("그런 사람 없습니다"));
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
