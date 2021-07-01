package com.leviosa.pumpkin.storage.repository;

import com.leviosa.pumpkin.storage.domain.Tag;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leviosa.pumpkin.db.gen.*; 

@Repository
public class TagsRepository {
    @Autowired
    DSLContext context;

    public Tag getTag(int id) {
        return context
        .selectFrom(Tables.TAGS)
        .where(Tables.TAGS.TAG_ID.eq(1L))
        .fetchAny()
        .into(Tag.class);
    }
}