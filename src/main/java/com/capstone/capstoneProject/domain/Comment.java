package com.capstone.capstoneProject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;


    private String youtuberName;
    private int subscriber;
    private String productName;
    private String productComment;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
