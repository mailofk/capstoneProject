package com.capstone.domain;

import jakarta.persistence.*;


@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long member_id;
    private long group_id;
    private long topic_id;

    private String id;
    private long password;

    private String description;
    private long profile;
    private String department;
}
