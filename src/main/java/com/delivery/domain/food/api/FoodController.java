package com.delivery.domain.food.api;

import com.delivery.domain.food.application.FoodService;
import com.delivery.domain.food.dto.request.FoodCreateRequest;
import com.delivery.domain.food.dto.request.FoodUpdateRequest;
import com.delivery.domain.food.dto.response.FoodCreateResponse;
import com.delivery.domain.food.dto.response.FoodFindAllResponse;
import com.delivery.domain.food.dto.response.FoodUpdateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "food", description = "Api food")
@RestController
@RequestMapping("/foods")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @Operation(summary = "create food", description = "create a food")
    @PostMapping
    public ResponseEntity<FoodCreateResponse> foodCreate(
            @Valid @RequestBody FoodCreateRequest foodCreateRequest) {
        FoodCreateResponse foodCreateResponse = foodService.createFood(foodCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(foodCreateResponse);
    }

    @Operation(summary = "find all food", description = "return list food")
    @GetMapping
    public List<FoodFindAllResponse> foodFindAll() {
        return foodService.findAllFood();
    }

    @Operation(summary = "update food", description = "update a food")
    @PutMapping("/{foodId}")
    public FoodUpdateResponse foodUpdate(
            @Valid @RequestBody FoodUpdateRequest foodUpdateRequest, @PathVariable Long foodId) {
        return foodService.updateFood(foodUpdateRequest, foodId);
    }
}
