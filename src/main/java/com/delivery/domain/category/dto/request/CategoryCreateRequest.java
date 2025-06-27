package com.delivery.domain.category.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record CategoryCreateRequest(
        @NotNull(message = "category not null")
                @Schema(name = "category name", description = "a category name")
                String name) {}
