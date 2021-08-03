package com.leviosa.pumpkin.storage.repository;

import com.leviosa.pumpkin.storage.domain.Tag;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.leviosa.pumpkin.db.gen.*; 

@Repository
public class TagsRepository {
    @Autowired
    DSLContext context;

    public Tag getTag(long id) {
        return context
        .selectFrom(Tables.TAGS)
        .where(Tables.TAGS.TAG_ID.eq(id))
        .fetchAny()
        .into(Tag.class);
    }

    public List<Tag> getTagsByLinkId(long linkId) {
        return context.select()
            .from(Tables.TAGS)
            .join(Tables.LINKS_TAGS)
            .on(Tables.TAGS.TAG_ID.eq(Tables.LINKS_TAGS.TAG_ID))
            .where(Tables.LINKS_TAGS.LINK_ID.eq(linkId))
            .fetch()
            .into(Tag.class);
    }

    public long create(Tag tag) {
        return context.insertInto(Tables.TAGS, Tables.TAGS.USER_ID, Tables.TAGS.NAME)
            .values(tag.getUserId(), tag.getName())
            .returningResult(Tables.TAGS.TAG_ID)
            .fetchOne()
            .getValue(Tables.TAGS.TAG_ID);
    }

    public void update(Tag tag) {
        context.update(Tables.TAGS)
            .set(Tables.TAGS.NAME, tag.getName())
            .where(Tables.TAGS.TAG_ID.eq(tag.getId()))
            .execute();
    }
}