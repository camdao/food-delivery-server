package com.delivery.domain.food.dao;

import static com.delivery.domain.food.domain.QFood.*;

import com.delivery.domain.food.domain.Food;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FoodRepositoryCustomImpl implements FoodRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Food> findByOwnerId(Long restaurantId) {
        return jpaQueryFactory.selectFrom(food).where(foodRestaurantEq(restaurantId)).fetch();
    }

    private BooleanExpression foodRestaurantEq(Long restaurantId) {
        return food.restaurant.id.eq(restaurantId);
    }
}
