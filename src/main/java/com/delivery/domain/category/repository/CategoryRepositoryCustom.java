package com.delivery.domain.category.repository;

import com.delivery.domain.category.domain.Category;
import java.util.List;

public interface CategoryRepositoryCustom {
    List<Category> categoryFindActive();
}
