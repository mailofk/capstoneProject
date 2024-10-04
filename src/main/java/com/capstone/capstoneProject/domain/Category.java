package com.capstone.capstoneProject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long id;

    @Column(name = "big_category")
    private String bigCategory;

    @Column(name = "mid_category")
    private String midCategory;

    @Column(name = "sml_category")
    private String smlCategory;

}
