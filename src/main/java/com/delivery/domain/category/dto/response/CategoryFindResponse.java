package com.delivery.domain.category.dto.response;

import com.delivery.domain.category.domain.Category;
import io.swagger.v3.oas.annotations.media.Schema;

public record CategoryFindResponse(
        @Schema(name = "category id", description = "a category id") Long id,
        @Schema(name = "category name", description = "a category name") String name) {

    public static CategoryFindResponse of(Category category) {
        return new CategoryFindResponse(category.getId(), category.getName());
    }
}
