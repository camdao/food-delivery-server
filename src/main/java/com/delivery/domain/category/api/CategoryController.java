package com.delivery.domain.category.api;

import com.delivery.domain.category.application.CategoryService;
import com.delivery.domain.category.dto.request.CategoryCreateRequest;
import com.delivery.domain.category.dto.request.CategoryUpdateRequest;
import com.delivery.domain.category.dto.response.CategoryCreateResponse;
import com.delivery.domain.category.dto.response.CategoryFindResponse;
import com.delivery.domain.category.dto.response.CategoryUpdateResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("categories")
@RestController
@RequiredArgsConstructor
@Tag(name = "category", description = "api category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("")
    public CategoryCreateResponse categoryCreate(
            @RequestBody @Valid CategoryCreateRequest categoryCreateRequest) {
        return categoryService.createCategory(categoryCreateRequest);
    }

    @GetMapping("")
    public List<CategoryFindResponse> categoryFind() {
        return categoryService.findCategory();
    }

    @PutMapping
    public CategoryUpdateResponse categoryUpdateResponse(
            @PathVariable Long categoryId,
            @RequestBody @Valid CategoryUpdateRequest categoryUpdateRequest) {
        return categoryService.updateCategory(categoryId, categoryUpdateRequest);
    }
}
