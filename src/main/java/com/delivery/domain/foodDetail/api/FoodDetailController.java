package com.delivery.domain.foodDetail.api;

import com.delivery.domain.foodDetail.application.FoodDetailService;
import com.delivery.domain.foodDetail.dto.request.FoodDetailCreateRequest;
import com.delivery.domain.foodDetail.dto.request.FoodDetailUpdateRequest;
import com.delivery.domain.foodDetail.dto.response.FoodDetailCreateResponse;
import com.delivery.domain.foodDetail.dto.response.FoodDetailFindResponse;
import com.delivery.domain.foodDetail.dto.response.FoodDetailUpdateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food-details")
@Tag(name = "food detail", description = "Api food detail")
public class FoodDetailController {
    private final FoodDetailService foodDetailService;

    @Operation(summary = "create food detail", description = "create a detail food")
    @PostMapping("")
    public FoodDetailCreateResponse foodCreate(
            @Valid @RequestBody FoodDetailCreateRequest foodDetailCreateRequest) {
        return foodDetailService.createFoodDetail(foodDetailCreateRequest);
    }

    @Operation(summary = "find food detail", description = "find list food detail")
    @GetMapping("/{foodId}")
    public List<FoodDetailFindResponse> foodDetailFind(@PathVariable Long foodId) {
        return foodDetailService.findFoodDetail(foodId);
    }

    @Operation(summary = "update food", description = "update a food")
    @PutMapping("/{foodDetailId}")
    public FoodDetailUpdateResponse foodDetailUpdate(
            @Valid @RequestBody FoodDetailUpdateRequest foodUpdateDetailRequest,
            @PathVariable Long foodDetailId) {
        return foodDetailService.updateFood(foodUpdateDetailRequest, foodDetailId);
    }
}
