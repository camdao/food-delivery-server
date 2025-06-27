package com.delivery.domain.category.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.delivery.domain.category.dto.request.CategoryCreateRequest;
import com.delivery.domain.category.dto.response.CategoryCreateResponse;
import com.delivery.domain.category.repository.CategoryRepository;
import com.delivery.domain.food.application.FoodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryServiceTest {

    @Autowired private FoodService foodService;

    @Autowired private CategoryService categoryService;

    @Autowired private CategoryRepository categoryRepository;

    @Test
    void create_a_category() {
        // given
        CategoryCreateRequest createRequest = new CategoryCreateRequest("name");

        // when
        CategoryCreateResponse categoryCreateResponse =
                categoryService.createCategory(createRequest);

        // then
        assertEquals("name", categoryCreateResponse.name());
    }
}
