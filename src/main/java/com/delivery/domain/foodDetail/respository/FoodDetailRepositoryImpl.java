package com.delivery.domain.foodDetail.respository;

import static com.delivery.domain.foodDetail.domain.QFoodDetail.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FoodDetailRepositoryImpl implements FoodDetailRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
}
