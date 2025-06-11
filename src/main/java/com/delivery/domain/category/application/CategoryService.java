package com.delivery.domain.category.application;

import com.delivery.domain.category.domain.Category;
import com.delivery.domain.category.dto.request.CategoryCreateRequest;
import com.delivery.domain.category.dto.response.CategoryCreateResponse;
import com.delivery.domain.category.dto.response.CategoryFindResponse;
import com.delivery.domain.category.repository.CategoryRepository;
import com.delivery.domain.food.dao.FoodRepository;
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
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryFindResponse::of).toList();
    }
}
