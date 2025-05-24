package com.delivery.domain.food.domain;

import com.delivery.domain.model.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Builder(access = AccessLevel.PRIVATE)
    private Food(String name, Long price, String foodImage) {
        this.name = name;
        this.price = price;
        this.foodImage = foodImage;
    }

    public static Food createFood(String name, Long price, String foodImage) {
        return Food.builder().name(name).price(price).foodImage(foodImage).build();
    }
}
