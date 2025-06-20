package com.delivery.domain.foodDetail.dao;

import com.delivery.domain.foodDetail.domain.FoodDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodDetailRepository
        extends JpaRepository<FoodDetail, Long>, FoodDetailRepositoryCustom {}
