package com.delivery.domain.foodDetail.domain;

import com.delivery.domain.food.domain.Food;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_detail_id")
    private Long id;

    private Long price;

    private String size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    @Builder(access = AccessLevel.PRIVATE)
    private FoodDetail(Long price, String size, Food food) {
        this.price = price;
        this.size = size;
        this.food = food;
    }

    public static FoodDetail createFoodDetail(Long price, String size, Food food) {
        return FoodDetail.builder().price(price).size(size).food(food).build();
    }
}
