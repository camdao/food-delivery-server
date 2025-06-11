package com.delivery.domain.category.dto.response;

import com.delivery.domain.category.domain.Category;
import com.delivery.domain.category.domain.CategoryStatus;
import io.swagger.v3.oas.annotations.media.Schema;

public record CategoryUpdateResponse(
        @Schema(name = "id", description = "category id") Long id,
        @Schema(name = "name", description = "category name") String name,
        @Schema(name = "status", description = "category status") CategoryStatus status) {
    public static CategoryUpdateResponse from(Category category) {
        return new CategoryUpdateResponse(
                category.getId(), category.getName(), category.getStatus());
    }
}
