package com.delivery.domain.category.repository;

import com.delivery.domain.category.domain.Category;

public interface CategoryRepositoryCustom {
    Category categoryFindOne(Long id);
}
