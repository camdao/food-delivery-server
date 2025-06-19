package com.delivery.domain.food.domain;

import com.delivery.domain.category.domain.Category;
import com.delivery.domain.foodDetail.domain.FoodDetail;
import com.delivery.domain.model.BaseTimeEntity;
import com.delivery.domain.restaurant.domain.Restaurant;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
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
    @Column(name = "food_id")
    private Long id;

    private String name;

    private String foodImage;

    private String describe;

    @Enumerated(EnumType.STRING)
    private FoodStatus status;

    @OneToMany(mappedBy = "food")
    private List<FoodDetail> foodDetails = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder(access = AccessLevel.PRIVATE)
    private Food(String name, String foodImage, String describe, FoodStatus status) {
        this.name = name;
        this.foodImage = foodImage;
        this.status = status;
        this.describe = describe;
    }

    public static Food createFood(String name, String describe) {
        return Food.builder().name(name).describe(describe).status(FoodStatus.AVAILABLE).build();
    }

    public void updateFood(String name, FoodStatus status) {
        this.name = name;
        this.status = status;
    }
}
