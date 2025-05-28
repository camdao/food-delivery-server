package com.delivery.domain.food.dao;

import com.delivery.domain.food.domain.Food;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByMemberId(Long memberId);
}
