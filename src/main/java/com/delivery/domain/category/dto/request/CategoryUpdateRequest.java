package com.delivery.domain.category.dto.request;

import com.delivery.domain.category.domain.CategoryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record CategoryUpdateRequest(
        @NotNull(message = "name category not null")
                @Schema(name = "name", description = "name category")
                String name,
        @NotNull(message = "status category not null")
                @Schema(name = "status", description = "status category")
                CategoryStatus status) {}
