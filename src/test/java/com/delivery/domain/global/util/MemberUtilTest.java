package com.delivery.domain.global.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.delivery.global.util.SecurityUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
public class MemberUtilTest {
    @MockitoBean private SecurityUtil securityUtil;

    @Test
    void get_Current_Member() {
        // given
        Long memberId = securityUtil.getCurrentMemberId();
        // then,when
        assertEquals(memberId, 0L);
    }
}
