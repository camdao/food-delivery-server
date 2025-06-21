package com.delivery.domain.restaurant.api;

import com.delivery.domain.restaurant.application.RestaurantService;
import com.delivery.domain.restaurant.dto.request.RestaurantCreateRequest;
import com.delivery.domain.restaurant.dto.request.RestaurantUpdateRequest;
import com.delivery.domain.restaurant.dto.response.RestaurantCreateResponse;
import com.delivery.domain.restaurant.dto.response.RestaurantFindResponse;
import com.delivery.domain.restaurant.dto.response.RestaurantUpdateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "restaurant", description = "api restaurant")
@RequestMapping("restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("")
    @Operation(summary = "create restaurant", description = "create a restaurant")
    public RestaurantCreateResponse createRestaurant(
            @Valid @RequestBody RestaurantCreateRequest restaurantCreateRequest) {
        return restaurantService.createRestaurant(restaurantCreateRequest);
    }

    @Operation(summary = "find restaurant", description = "find a restaurant")
    @GetMapping("/{restaurantId}")
    public RestaurantFindResponse restaurantFind(@PathVariable Long restaurantId) {
        return restaurantService.findRestaurant(restaurantId);
    }

    @Operation(summary = "update restaurant", description = "update a restaurant")
    @PostMapping("/update/{restaurantId}")
    public RestaurantUpdateResponse restaurantUpdate(
            @PathVariable Long restaurantId,
            @Valid @RequestBody RestaurantUpdateRequest restaurantUpdateRequest) {
        return restaurantService.updateRestaurant(restaurantId, restaurantUpdateRequest);
    }

    @Operation(summary = "delete restaurant", description = "delete restaurant")
    @DeleteMapping("/{restaurantId}")
    public void deleteRestaurant(@PathVariable Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
    }
}
