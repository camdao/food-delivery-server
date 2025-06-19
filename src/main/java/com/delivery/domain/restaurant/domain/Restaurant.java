package com.delivery.domain.restaurant.domain;

import com.delivery.domain.food.domain.Food;
import com.delivery.domain.member.domain.Member;
import jakarta.persistence.*;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id;

    private String name;

    private String describe;

    private String imgUrl;

    @OneToMany(mappedBy = "restaurant")
    private List<Food> foods;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private Member owner;

    @Builder
    public Restaurant(String name, String describe, String imgUrl) {
        this.name = name;
        this.describe = describe;
        this.imgUrl = imgUrl;
    }

    public static Restaurant createRestaurant(String name, String describe) {
        return Restaurant.builder().name(name).describe(describe).build();
    }

    public void update(String name, String describe) {
        this.name = name;
        this.describe = describe;
    }
}
