package com.delivery.domain.category.dto.response;

import com.delivery.domain.category.domain.Category;
import io.swagger.v3.oas.annotations.media.Schema;

public record CategoryFindOneResponse(
        @Schema(name = "category id", description = "a category id") Long id,
        @Schema(name = "category name", description = "a category name") String name,
        @Schema(name = "food id", description = "food id of category") Long foodId) {

    public static CategoryFindOneResponse from(Long foodId, Category category) {
        return new CategoryFindOneResponse(category.getId(), category.getName(), foodId);
    }
}
