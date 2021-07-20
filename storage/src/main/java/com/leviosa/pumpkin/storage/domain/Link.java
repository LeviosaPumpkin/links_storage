package com.leviosa.pumpkin.storage.domain;

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
}