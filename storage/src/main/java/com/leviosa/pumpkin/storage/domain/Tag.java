package com.leviosa.pumpkin.storage.domain;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Tag {
    @Column(name = "tag_id")
    private long id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "name")
    private String name;

    public Tag(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Tag(long id, int userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }
}