package com.delivery.domain.food.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.delivery.domain.food.application.FoodService;
import com.delivery.domain.food.dao.FoodRepository;
import com.delivery.domain.food.domain.Food;
import com.delivery.domain.food.dto.request.FoodCreateRequest;
import com.delivery.domain.food.dto.response.FoodCreateResponse;
import com.delivery.domain.food.dto.response.FoodFindAllResponse;
import com.delivery.domain.member.dao.MemberRepository;
import com.delivery.domain.member.domain.Member;
import com.delivery.global.config.security.PrincipalDetails;
import com.delivery.global.util.MemberUtil;
import java.util.List;
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
    @Autowired private FoodRepository foodRepository;
    @Autowired private MemberUtil memberUtil;

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

    @Test
    void find_all_food() {
        // given
        for (Long i = 0L; i < 5; i++) {
            FoodCreateRequest foodCreateRequest = new FoodCreateRequest("name", i);
            Food food =
                    foodRepository.save(
                            Food.createFood(
                                    foodCreateRequest.name(),
                                    foodCreateRequest.price(),
                                    memberUtil.getCurrentMember()));
        }

        // when
        List<FoodFindAllResponse> foodList = foodService.FoodFindAll();

        // then
        assertThat(foodList.size()).isEqualTo(5);
        assertThat(foodList)
                .extracting("id", "name", "price")
                .containsExactlyInAnyOrder(
                        tuple(1L, "name", 0L),
                        tuple(2L, "name", 1L),
                        tuple(3L, "name", 2L),
                        tuple(4L, "name", 3L),
                        tuple(5L, "name", 4L));
    }
}
