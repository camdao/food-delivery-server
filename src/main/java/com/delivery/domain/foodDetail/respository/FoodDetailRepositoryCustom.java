package com.delivery.domain.foodDetail.respository;

import com.delivery.domain.foodDetail.domain.FoodDetail;
import java.util.List;

public interface FoodDetailRepositoryCustom {
    List<FoodDetail> findAllByFood(Long foodId);
}
