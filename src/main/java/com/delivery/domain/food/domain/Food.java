package com.delivery.domain.food.domain;

import com.delivery.domain.member.domain.Member;
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

    private String name;

    private Long price;

    private String foodImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private FoodStatus status;

    @Builder(access = AccessLevel.PRIVATE)
    private Food(String name, Long price, String foodImage, FoodStatus status, Member member) {
        this.name = name;
        this.price = price;
        this.foodImage = foodImage;
        this.status = status;
        this.member = member;
    }

    public static Food createFood(String name, Long price, Member member) {
        return Food.builder().name(name).price(price).status(FoodStatus.AVAILABLE).build();
    }
}
