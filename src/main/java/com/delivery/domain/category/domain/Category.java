package com.delivery.domain.category.domain;

import com.delivery.domain.food.domain.Food;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    @Builder(access = AccessLevel.PRIVATE)
    Category(String name, Food food) {
        this.name = name;
        this.food = food;
    }

    public static Category createCategory(String name, Food food) {
        return Category.builder().name(name).food(food).build();
    }
}
