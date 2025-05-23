package com.delivery.domain.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberRole {
    USER("USER"),
    ADMIN("ADMIN"),
    CHEF("CHEF");
    private final String value;
}
