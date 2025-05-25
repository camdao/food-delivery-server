package com.delivery.domain.food.domain;

import com.delivery.domain.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Food extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    String name;

    Long price;

    String foodImage;

    @Enumerated(EnumType.STRING)
    private FoodStatus status;

    @Builder(access = AccessLevel.PRIVATE)
    private Food(String name, Long price, String foodImage, FoodStatus status) {
        this.name = name;
        this.price = price;
        this.foodImage = foodImage;
        this.status = status;
    }

    public static Food createFood(String name, Long price, String foodImage) {
        return Food.builder()
                .name(name)
                .price(price)
                .foodImage(foodImage)
                .status(FoodStatus.AVAILABLE)
                .build();
    }
}
