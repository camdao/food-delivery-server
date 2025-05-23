package com.delivery.global.util;

import com.delivery.domain.member.dao.MemberRepository;
import com.delivery.domain.member.domain.Member;
import com.delivery.global.config.erro.exception.CustomException;
import com.delivery.global.config.erro.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberUtil {
    private final SecurityUtil securityUtil;
    private final MemberRepository memberRepository;

    // TODO: Delete after database connection and fixture data insertion
    private void insertMockMemberIfNotExist() {
        if (memberRepository.count() != 0) {
            return;
        }

        Member member = Member.createNormalMember("testNickname");
        memberRepository.save(member);
    }

    public Member getCurrentMember() {
        insertMockMemberIfNotExist();
        return memberRepository
                .findById(securityUtil.getCurrentMemberId())
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
    }
}
