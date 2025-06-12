package com.delivery.domain.foodDetail.respository;

import static com.delivery.domain.foodDetail.domain.QFoodDetail.*;

import com.delivery.domain.foodDetail.domain.FoodDetail;
import com.delivery.domain.foodDetail.domain.FoodDetailStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FoodDetailRepositoryImpl implements FoodDetailRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<FoodDetail> findAllByFood(Long foodId) {
        return jpaQueryFactory
                .selectFrom(foodDetail)
                .where(foodIdEq(foodId), foodStatusEq(FoodDetailStatus.AVAILABLE))
                .fetch();
    }

    private BooleanExpression foodIdEq(Long foodId) {
        return foodDetail.food.id.eq(foodId);
    }

    private BooleanExpression foodStatusEq(FoodDetailStatus status) {
        return foodDetail.status.eq(status);
    }
}
