package com.capstone.capstoneProject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;

    //연결할 category (임시세팅) --> 추후에는 many-to-many로 연결해서 연동할 예정
    private String smlCategory;
    //연결할 comment

    private String productName;
    private String link;

    //제품 스펙 가져올 것들
    private String cpu;
    private String ram;
    private String capacity;
    private String weight;


    @OneToMany(mappedBy = "product")
    private List<Comment> commentList = new ArrayList<Comment>();

    public void addComment(Comment comment) {
        commentList.add(comment);
        comment.setProduct(this);
    }


}
