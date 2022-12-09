package com.example.demo.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손놈"),
    USER("ROLE_USER","유저");

    private final String key;
    private final String value;
}
