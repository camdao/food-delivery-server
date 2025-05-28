package com.delivery.domain.food.api;

import com.delivery.domain.food.application.FoodService;
import com.delivery.domain.food.dto.request.FoodCreateRequest;
import com.delivery.domain.food.dto.response.FoodCreateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "food", description = "Api food")
@RestController
@RequestMapping("/foods")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @Operation(summary = "create food", description = "create a food")
    @PostMapping
    public ResponseEntity<FoodCreateRequest> foodCreate(
            @Valid @RequestBody FoodCreateRequest foodCreateRequest) {
        FoodCreateResponse foodCreateResponse = foodService.createFood(foodCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(foodCreateRequest);
    }
}
