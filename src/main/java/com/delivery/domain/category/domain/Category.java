package com.delivery.domain.category.domain;

import com.delivery.domain.food.domain.Food;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    Category(String name, Food food) {
        this.name = name;
    }

    public static Category createCategory(String name) {
        return Category.builder().name(name).build();
    }
}
