package com.delivery.domain.food.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.delivery.domain.category.domain.Category;
import com.delivery.domain.category.repository.CategoryRepository;
import com.delivery.domain.food.application.FoodService;
import com.delivery.domain.food.domain.FoodStatus;
import com.delivery.domain.food.dto.request.FoodCreateRequest;
import com.delivery.domain.food.dto.request.FoodUpdateRequest;
import com.delivery.domain.food.dto.response.FoodCreateResponse;
import com.delivery.domain.food.dto.response.FoodFindAllResponse;
import com.delivery.domain.food.dto.response.FoodUpdateResponse;
import com.delivery.domain.member.dao.MemberRepository;
import com.delivery.domain.member.domain.Member;
import com.delivery.domain.restaurant.application.RestaurantService;
import com.delivery.domain.restaurant.dto.request.RestaurantCreateRequest;
import com.delivery.global.config.security.PrincipalDetails;
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
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private RestaurantService restaurantService;

    @BeforeEach
    void setUp() {

        Member member = Member.createNormalMember("nickname");
        Member saveMember = memberRepository.save(member);

        PrincipalDetails principal = new PrincipalDetails(saveMember.getId(), "USER");
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        principal, null, principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void create_a_food() {
        // given
        Category category = categoryRepository.save(Category.createCategory("name"));
        restaurantService.createRestaurant(new RestaurantCreateRequest("name", "describe"));
        FoodCreateRequest foodCreateRequest =
                new FoodCreateRequest("name", "describe", category.getId());

        // when
        FoodCreateResponse food = foodService.createFood(foodCreateRequest);

        // then
        assertNotNull(food);
        assertEquals("name", food.name());
        assertEquals("describe", food.describe());
        assertEquals(FoodStatus.AVAILABLE, food.status());
    }

    @Test
    void find_all_food() {
        // given
        Category category = categoryRepository.save(Category.createCategory("name"));
        restaurantService.createRestaurant(new RestaurantCreateRequest("name", "describe"));

        for (Long i = 0L; i < 5; i++) {
            FoodCreateRequest foodCreateRequest =
                    new FoodCreateRequest("name", "describe", category.getId());
            foodService.createFood(foodCreateRequest);
        }

        // when
        List<FoodFindAllResponse> foodList = foodService.findAllFood();

        // then
        assertThat(foodList.size()).isEqualTo(5);
        assertThat(foodList)
                .extracting("id", "name")
                .containsExactlyInAnyOrder(
                        tuple(1L, "name"),
                        tuple(2L, "name"),
                        tuple(3L, "name"),
                        tuple(4L, "name"),
                        tuple(5L, "name"));
    }

    @Test
    void update_a_food() {
        // given
        FoodUpdateRequest foodUpdateRequest =
                new FoodUpdateRequest("name", "update describe", FoodStatus.DISCONTINUED);

        Category category = categoryRepository.save(Category.createCategory("name"));
        restaurantService.createRestaurant(new RestaurantCreateRequest("name", "describe"));

        FoodCreateRequest foodCreateRequest =
                new FoodCreateRequest("name", "describe", category.getId());

        FoodCreateResponse foodCreateResponse = foodService.createFood(foodCreateRequest);

        // then
        FoodUpdateResponse foodUpdateResponse =
                foodService.updateFood(foodUpdateRequest, foodCreateResponse.id());

        // when
        assertNotNull(foodUpdateResponse);
        assertEquals(FoodStatus.DISCONTINUED, foodUpdateResponse.status());
    }
}
