package com.delivery.domain.foodDetail.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.delivery.domain.category.domain.Category;
import com.delivery.domain.category.repository.CategoryRepository;
import com.delivery.domain.food.application.FoodService;
import com.delivery.domain.food.dto.request.FoodCreateRequest;
import com.delivery.domain.food.dto.response.FoodCreateResponse;
import com.delivery.domain.foodDetail.dto.request.FoodDetailCreateRequest;
import com.delivery.domain.foodDetail.dto.response.FoodDetailCreateResponse;
import com.delivery.domain.foodDetail.dto.response.FoodDetailFindResponse;
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
public class FoodDetailServiceTest {
    @Autowired private MemberRepository memberRepository;

    @Autowired private FoodService foodService;

    @Autowired private FoodDetailService foodDetailService;

    @Autowired private RestaurantService restaurantService;

    @Autowired private CategoryRepository categoryRepository;

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

        FoodCreateResponse food = foodService.createFood(foodCreateRequest);
        FoodDetailCreateRequest foodDetailCreateRequest =
                new FoodDetailCreateRequest(1L, "xs", food.id());

        // when
        FoodDetailCreateResponse foodDetailCreateResponse =
                foodDetailService.createFoodDetail(foodDetailCreateRequest);

        // then
        assertEquals("xs", foodDetailCreateResponse.size());
    }

    @Test
    void test_find_food_detail() {
        // given
        Category category = categoryRepository.save(Category.createCategory("name"));

        restaurantService.createRestaurant(new RestaurantCreateRequest("name", "describe"));

        FoodCreateRequest foodCreateRequest =
                new FoodCreateRequest("name", "describe", category.getId());
        FoodCreateResponse food = foodService.createFood(foodCreateRequest);

        for (Long i = 0L; i < 5; i++) {
            FoodDetailCreateRequest foodDetailCreateRequest =
                    new FoodDetailCreateRequest(1L, "xs", food.id());
            foodDetailService.createFoodDetail(foodDetailCreateRequest);
        }

        // when
        List<FoodDetailFindResponse> foodDetails = foodDetailService.findFoodDetail(food.id());
        // then
        assertNotNull(foodDetails);
        assertEquals(5, foodDetails.size());
    }
}
