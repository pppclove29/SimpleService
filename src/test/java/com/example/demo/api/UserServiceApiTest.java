package com.example.demo.api;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserDto;
import com.example.demo.domain.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceApiTest {

    @Autowired
    UserServiceApi userServiceApi;

    @Autowired
    UserRepository userRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    String url;

    @BeforeEach
    public void setURL() {
        url = "http://localhost:" + port + "/api/v1/user";
    }
    @AfterEach
    public void clearDB(){
        userRepository.deleteAll();
    }

    //@Test  json이나 알고 나중에 하자
    public void 회원가입API테스트() {

        String name = "정범준";
        String password = "정범준 비밀번호";

        UserDto userDto = UserDto.builder()
                .name(name)
                .password(password)
                .build();

        // 실질적인 Http 통신 엔티티를 생성한다. 헤더와 바디로 이루어져있다 둘 다 get메소드를 통해 얻어올수있다
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(userDto);

        url += "/register";

        // HttpStatusCode 상태를 추가하여 HttpEntity를 확장한다
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, httpEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0);

        User user = userRepository.findById(1L).orElseThrow(
                () -> new IllegalArgumentException("그런 사람 없다")
        ); // 즉시 초기화

        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getPassword()).isEqualTo(password);
    }
}
