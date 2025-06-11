package com.delivery.domain.category.repository;

import static com.delivery.domain.category.domain.QCategory.*;

import com.delivery.domain.category.domain.Category;
import com.delivery.domain.category.domain.CategoryStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Category> categoryFindActive() {
        return jpaQueryFactory
                .selectFrom(category)
                .where(categoryStatusEq(CategoryStatus.ACTIVE))
                .fetch();
    }

    private BooleanExpression categoryStatusEq(CategoryStatus status) {
        return category.status.eq(status);
    }
}
