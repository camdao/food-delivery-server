package com.delivery.domain.restaurant.dao;

import com.delivery.domain.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {}
