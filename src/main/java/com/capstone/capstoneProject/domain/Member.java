package com.capstone.capstoneProject.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Member {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    //사용할 이메일
    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String name;



    // 추후 Product와 Member를 ManyToMany로 연결 시도
//    @OneToMany(mappedBy = "product_id")
//    private List<Product> productList;


    public Member(){

    }

    public Member(String email, Role role) {
        this.email = email;
        this.role = role;
    }
}
