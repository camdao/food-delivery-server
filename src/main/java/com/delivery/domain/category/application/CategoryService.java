package com.delivery.domain.category.application;

import com.delivery.domain.category.domain.Category;
import com.delivery.domain.category.dto.request.CategoryCreateRequest;
import com.delivery.domain.category.dto.response.CategoryCreateResponse;
import com.delivery.domain.category.dto.response.CategoryFindOneResponse;
import com.delivery.domain.category.repository.CategoryRepository;
import com.delivery.domain.food.dao.FoodRepository;
import com.delivery.domain.food.domain.Food;
import com.delivery.global.config.erro.exception.CustomException;
import com.delivery.global.config.erro.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final FoodRepository foodRepository;

    public CategoryCreateResponse createCategory(CategoryCreateRequest createRequest) {
        Food food =
                foodRepository
                        .findById(createRequest.foodId())
                        .orElseThrow(() -> new CustomException(ErrorCode.Food_NOT_FOUND));
        Category category =
                categoryRepository.save(Category.createCategory(createRequest.name(), food));

        return CategoryCreateResponse.from(food.getId(), category);
    }

    @Transactional(readOnly = true)
    public CategoryFindOneResponse findOneCategory(Long categoryId) {
        Category category = categoryRepository.categoryFindOne(categoryId);
        return CategoryFindOneResponse.from(category.getFood().getId(), category);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
