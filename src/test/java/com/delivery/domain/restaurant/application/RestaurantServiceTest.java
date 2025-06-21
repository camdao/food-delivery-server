package com.delivery.domain.restaurant.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.delivery.domain.member.dao.MemberRepository;
import com.delivery.domain.member.domain.Member;
import com.delivery.domain.restaurant.dto.request.RestaurantCreateRequest;
import com.delivery.domain.restaurant.dto.request.RestaurantUpdateRequest;
import com.delivery.domain.restaurant.dto.response.RestaurantCreateResponse;
import com.delivery.domain.restaurant.dto.response.RestaurantFindResponse;
import com.delivery.domain.restaurant.dto.response.RestaurantUpdateResponse;
import com.delivery.global.config.security.PrincipalDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootTest
public class RestaurantServiceTest {
    @Autowired RestaurantService restaurantService;

    @Autowired private MemberRepository memberRepository;

    @BeforeEach
    public void setUp() {
        Member member = Member.createNormalMember("nickname");
        Member saveMember = memberRepository.save(member);

        PrincipalDetails principal = new PrincipalDetails(saveMember.getId(), "USER");
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        principal, null, principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void create_a_restaurant() {
        // given
        RestaurantCreateRequest restaurantCreateRequest =
                new RestaurantCreateRequest("name", "describe");

        // when
        RestaurantCreateResponse restaurantCreateResponse =
                restaurantService.createRestaurant(restaurantCreateRequest);

        // then
        assertEquals(restaurantCreateRequest.name(), restaurantCreateResponse.name());
    }

    @Test
    void find_a_restaurant() {
        // given
        RestaurantCreateRequest restaurantCreateRequest =
                new RestaurantCreateRequest("name", "describe");
        RestaurantCreateResponse restaurantCreateResponse =
                restaurantService.createRestaurant(restaurantCreateRequest);

        // when
        RestaurantFindResponse restaurantFindResponse =
                restaurantService.findRestaurant(restaurantCreateResponse.id());
        // then
        assertEquals(restaurantFindResponse.name(), restaurantCreateResponse.name());
    }

    @Test
    void update_a_restaurant() {
        // given
        RestaurantCreateRequest restaurantCreateRequest =
                new RestaurantCreateRequest("name", "describe");
        RestaurantCreateResponse restaurantCreateResponse =
                restaurantService.createRestaurant(restaurantCreateRequest);

        RestaurantUpdateRequest restaurantUpdateRequest =
                new RestaurantUpdateRequest("name update", "describe update");
        // when
        RestaurantUpdateResponse restaurantUpdateResponse =
                restaurantService.updateRestaurant(
                        restaurantCreateResponse.id(), restaurantUpdateRequest);
        // then
        assertEquals(restaurantUpdateResponse.name(), restaurantUpdateRequest.name());
    }
}
