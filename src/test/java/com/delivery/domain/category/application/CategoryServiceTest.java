package com.delivery.domain.category.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.delivery.domain.category.domain.CategoryStatus;
import com.delivery.domain.category.dto.request.CategoryCreateRequest;
import com.delivery.domain.category.dto.request.CategoryUpdateRequest;
import com.delivery.domain.category.dto.response.CategoryCreateResponse;
import com.delivery.domain.category.dto.response.CategoryFindResponse;
import com.delivery.domain.category.dto.response.CategoryUpdateResponse;
import com.delivery.domain.category.repository.CategoryRepository;
import com.delivery.domain.food.application.FoodService;
import java.util.List;
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

    @Test
    void find_a_category() {
        // given
        CategoryCreateRequest createRequest = new CategoryCreateRequest("name");
        CategoryCreateResponse categoryCreateResponse =
                categoryService.createCategory(createRequest);

        // when
        List<CategoryFindResponse> categoryList = categoryService.findCategory();

        // then
        assertThat(categoryList.size()).isEqualTo(1);
        assertThat(categoryList)
                .extracting("id", "name")
                .containsExactlyInAnyOrder(tuple(categoryCreateResponse.id(), "name"));
    }

    @Test
    void update_a_category() {
        // given
        CategoryCreateRequest createRequest = new CategoryCreateRequest("name");
        CategoryCreateResponse categoryCreateResponse =
                categoryService.createCategory(createRequest);

        CategoryUpdateRequest updateRequest =
                new CategoryUpdateRequest("nameUpdate", CategoryStatus.ACTIVE);

        // when
        CategoryUpdateResponse categoryUpdateResponse =
                categoryService.updateCategory(categoryCreateResponse.id(), updateRequest);

        // then
        assertEquals("nameUpdate", categoryUpdateResponse.name());
    }
}
