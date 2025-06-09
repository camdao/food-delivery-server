package com.delivery.domain.foodDetail.api;

import com.delivery.domain.foodDetail.application.FoodDetailService;
import com.delivery.domain.foodDetail.dto.request.FoodDetailCreateRequest;
import com.delivery.domain.foodDetail.dto.response.FoodDetailCreateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food-details")
@Tag(name = "food detail", description = "Api food detail")
public class FoodDetailController {
    private final FoodDetailService foodDetailService;

    @Operation(summary = "create food detail", description = "create a detail food")
    @PostMapping("")
    public FoodDetailCreateResponse foodCreate(FoodDetailCreateRequest foodDetailCreateRequest) {
        return foodDetailService.createFoodDetail(foodDetailCreateRequest);
    }
}
