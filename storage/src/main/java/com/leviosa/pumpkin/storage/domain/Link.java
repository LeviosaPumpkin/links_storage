package com.leviosa.pumpkin.storage.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link {
    private long id;
    private String link;
    private String description;
    private int userId;
    private List<Tag> tags;

    public Link(String link, String description, int userId, List<Tag> tags) {
        this.link = link;
        this.description = description;
        this.userId = userId;
        this.tags = new ArrayList<>();
        this.tags.addAll(tags);
    } 
}