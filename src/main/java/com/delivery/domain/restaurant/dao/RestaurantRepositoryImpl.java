package com.delivery.domain.restaurant.dao;

import static com.delivery.domain.restaurant.domain.QRestaurant.*;

import com.delivery.domain.restaurant.domain.Restaurant;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Restaurant findRestaurantById(Long id) {
        return jpaQueryFactory
                .selectFrom(restaurant)
                .where(restaurantIdEq(id))
                .join(restaurant.categories)
                .fetchJoin()
                .fetchOne();
    }

    private BooleanExpression restaurantIdEq(Long id) {
        return restaurant.id.eq(id);
    }
}
