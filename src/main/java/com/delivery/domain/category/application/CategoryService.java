package com.delivery.domain.category.application;

import com.delivery.domain.category.domain.Category;
import com.delivery.domain.category.dto.request.CategoryCreateRequest;
import com.delivery.domain.category.dto.request.CategoryUpdateRequest;
import com.delivery.domain.category.dto.response.CategoryCreateResponse;
import com.delivery.domain.category.dto.response.CategoryFindResponse;
import com.delivery.domain.category.dto.response.CategoryUpdateResponse;
import com.delivery.domain.category.repository.CategoryRepository;
import com.delivery.domain.food.dao.FoodRepository;
import com.delivery.global.config.erro.exception.CustomException;
import com.delivery.global.config.erro.exception.ErrorCode;
import java.util.List;
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
        Category category = categoryRepository.save(Category.createCategory(createRequest.name()));
        return CategoryCreateResponse.from(category);
    }

    public List<CategoryFindResponse> findCategory() {
        List<Category> categories = categoryRepository.categoryFindActive();
        return categories.stream().map(CategoryFindResponse::of).toList();
    }

    public CategoryUpdateResponse updateCategory(
            Long categoryId, CategoryUpdateRequest categoryUpdateRequest) {
        Category category =
                categoryRepository
                        .findById(categoryId)
                        .orElseThrow(() -> new CustomException(ErrorCode.Category_NOT_FOUND));
        category.updateCategory(categoryUpdateRequest.name(), categoryUpdateRequest.status());
        return CategoryUpdateResponse.from(category);
    }
}
