package com.example.demo.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("ROLE_ADMIN","관리자"),
    GUEST("ROLE_GUEST", "손놈"),
    USER("ROLE_USER","유저"),
    VIP("ROLE_VIP","단골유저");


    private final String key;
    private final String value;
}
