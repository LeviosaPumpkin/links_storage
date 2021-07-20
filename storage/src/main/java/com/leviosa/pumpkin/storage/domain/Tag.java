package com.leviosa.pumpkin.storage.domain;

import javax.persistence.Column;

import lombok.Data;

@Data
public class Tag {
    @Column(name = "tag_id")
    private long id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "name")
    private String name;
}