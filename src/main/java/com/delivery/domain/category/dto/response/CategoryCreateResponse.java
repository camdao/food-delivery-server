package com.delivery.domain.category.dto.response;

import com.delivery.domain.category.domain.Category;
import io.swagger.v3.oas.annotations.media.Schema;

public record CategoryCreateResponse(
        @Schema(name = "category id", description = "a category id") Long id,
        @Schema(name = "category name", description = "a category name") String name,
        @Schema(name = "food id", description = "food id of category") Long foodId) {
    public static CategoryCreateResponse form(Long foodId, Category category) {
        return new CategoryCreateResponse(category.getId(), category.getName(), foodId);
    }
}
