package com.capstone.capstoneProject.domain;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String password;

    //사용할 이메일 --> 필수일 필요까지는 없을 듯함
    private String email;

    // 추후 Product와 Member를 ManyToMany로 연결 시도
//    @OneToMany(mappedBy = "product_id")
//    private List<Product> productList;



    public Member(String password, String email) {
        this.password = password;
        this.email = email;
    }
}
