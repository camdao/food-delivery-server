package com.delivery.domain.member.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MemberTest {
    @Test
    void create_a_member_normal() {
        // give
        Member member = Member.createNormalMember("nameTest");
        // then,when
        assertEquals(member.getStatus(), MemberStatus.NORMAL);
    }
}
