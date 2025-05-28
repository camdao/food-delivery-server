package com.delivery.domain.member.domain;

import com.delivery.domain.food.domain.Food;
import com.delivery.domain.model.BaseTimeEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    private String fullName;

    private String profileImage;

    private String phone;

    private String bio;

    private LocalDateTime lastLoginAt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Food> foods = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private Member(
            String email,
            MemberRole role,
            MemberStatus status,
            String fullName,
            String profileImage,
            String phone,
            String bio,
            LocalDateTime lastLoginAt) {

        this.fullName = fullName;
        this.role = role;
        this.status = status;
        this.profileImage = profileImage;
        this.phone = phone;
        this.lastLoginAt = lastLoginAt;
        this.bio = bio;
    }

    public static Member createNormalMember(String fullName) {
        return Member.builder()
                .fullName(fullName)
                .status(MemberStatus.NORMAL)
                .role(MemberRole.USER)
                .lastLoginAt(LocalDateTime.now())
                .build();
    }
}
