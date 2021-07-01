package com.leviosa.pumpkin.storage.domain;

import java.util.List;

import lombok.Data;

@Data
public class Link {
    private int id;
    private String link;
    private int userId;
    private List<Tag> tags;
}