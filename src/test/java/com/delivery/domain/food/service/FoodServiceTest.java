package com.delivery.domain.food.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.delivery.domain.food.application.FoodService;
import com.delivery.domain.food.dto.request.FoodCreateRequest;
import com.delivery.domain.food.dto.response.FoodCreateResponse;
import com.delivery.domain.member.dao.MemberRepository;
import com.delivery.domain.member.domain.Member;
import com.delivery.global.config.security.PrincipalDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootTest
public class FoodServiceTest {
    @Autowired private MemberRepository memberRepository;
    @Autowired private FoodService foodService;

    @BeforeEach
    void setUp() {
        PrincipalDetails principal = new PrincipalDetails(1L, "USER");
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        principal, null, principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Member member = Member.createNormalMember("nickname");
        memberRepository.save(member);
    }

    @Test
    void create_a_food() {
        // given
        FoodCreateRequest foodCreateRequest = new FoodCreateRequest("name", 1L);

        // when
        FoodCreateResponse food = foodService.createFood(foodCreateRequest);

        // then
        assertNotNull(food);
        assertEquals("name", food.name());
        assertEquals(1L, food.price());
    }
}
