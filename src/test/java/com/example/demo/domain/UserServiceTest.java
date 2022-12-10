package com.example.demo.domain;

import com.example.demo.domain.user.User;
import com.example.demo.api.dto.UserDto;
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
        //given
        String testName = "테스트 이름";
        String testPassword = "테스트 비밀번호";

        UserDto dto = createUser(testName,testPassword);

        //when
        Long id = userService.register(dto);

        //then
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
        //given
        String testName = "테스트 이름";
        String testPassword = "테스트 비밀번호";

        UserDto register_dto = createUser(testName,testPassword);

        Long r_id = userService.register(register_dto);

        UserDto login_dto = createUser(testName,testPassword);

        //when
        Long l_id = userService.login(login_dto);

        //then
        User user = userRepository.getReferenceById(l_id);

        assertThat(r_id).isEqualTo(l_id);
        assertThat(user.getName()).isEqualTo(testName);
        assertThat(user.getPassword()).isEqualTo(testPassword);
    }

    @Test
    public void 틀린비밀번호로그인테스트() {
        //given
        String testName = "테스트 이름";
        String testPassword = "테스트 비밀번호";

        UserDto dto = createUser(testName,testPassword);

        Long r_id = userService.register(dto);

        UserDto login_dto = createUser(testName,testPassword + "wrong");

        //when
        try {
            Long l_id = userService.login(login_dto);
        } catch (WrongPasswordException e) {
            //then
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void 회원정보변경테스트() {
        //given
        String testName = "테스트 이름";
        String testPassword = "테스트 비밀번호";

        UserDto dto = createUser(testName,testPassword);

        Long id = userService.register(dto);

        String newTestName = "새로운 테스트 이름";
        String newTestPassword = "새로운 테스트 비밀번호";

        UserDto new_dto = createUser(newTestName,newTestPassword);

        //when
        Long new_id = userService.changeConfig(id, new_dto);

        //then
        User user = userRepository.getReferenceById(new_id);

        assertThat(user.getName()).isEqualTo(newTestName);
        assertThat(user.getPassword()).isEqualTo(newTestPassword);
        assertThat(user.getId()).isEqualTo(id);
    }

    @Test
    public void 회원탈퇴테스트() {
        //given
        String testName = "테스트 이름";
        String testPassword = "테스트 비밀번호";

        UserDto dto = createUser(testName,testPassword);

        Long id = userService.register(dto);

        //when
        Long deleted_id = userService.withdraw(id,null);

        //then
        try{
            userRepository.findById(deleted_id).orElseThrow(
                    () -> new IllegalArgumentException("그런 사람 없습니다"));
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    UserDto createUser(String name, String password) {
        UserDto dto = new UserDto();
        dto.setName(name);
        dto.setPassword(password);

        return dto;
    }
}
