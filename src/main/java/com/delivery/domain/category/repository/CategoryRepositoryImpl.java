package com.delivery.domain.category.repository;

import static com.delivery.domain.category.domain.QCategory.*;

import com.delivery.domain.category.domain.Category;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Category categoryFindOne(Long id) {
        return jpaQueryFactory.selectFrom(category).where(categoryIdEq(id)).fetchOne();
    }

    private BooleanExpression categoryIdEq(Long id) {
        return category.food.id.eq(id);
    }
}
