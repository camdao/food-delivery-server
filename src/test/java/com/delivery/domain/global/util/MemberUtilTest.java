package com.delivery.domain.global.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.delivery.domain.member.dao.MemberRepository;
import com.delivery.domain.member.domain.Member;
import com.delivery.global.config.security.PrincipalDetails;
import com.delivery.global.util.MemberUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootTest
public class MemberUtilTest {
    @Autowired private MemberUtil memberUtil;
    @Autowired private MemberRepository memberRepository;

    @Test
    void get_information_of_currently_logged_in_member() {
        // given
        Member member = Member.createNormalMember("test");
        Member savedMember = memberRepository.save(member);

        PrincipalDetails principal = new PrincipalDetails(savedMember.getId(), "USER");
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        principal, "password", principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // when
        Member currentMember = memberUtil.getCurrentMember();
        // then
        assertEquals(savedMember.getId(), currentMember.getId());
    }
}
