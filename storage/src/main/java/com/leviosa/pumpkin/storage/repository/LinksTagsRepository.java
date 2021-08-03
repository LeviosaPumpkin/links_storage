package com.leviosa.pumpkin.storage.repository;

import java.util.List;
import java.util.stream.Collectors;

import com.leviosa.pumpkin.db.gen.Tables;
import com.leviosa.pumpkin.db.gen.tables.records.LinksTagsRecord;
import com.leviosa.pumpkin.storage.domain.Link;
import com.leviosa.pumpkin.storage.domain.Tag;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LinksTagsRepository {
    @Autowired
    private DSLContext context;

    public void create(Link link, long linkId) {        
        link.getTags().forEach(tag -> {
            context.insertInto(Tables.LINKS_TAGS, Tables.LINKS_TAGS.LINK_ID, Tables.LINKS_TAGS.TAG_ID)
                .values(linkId, tag.getId())
                .execute();
        });
    }

    public void update(Link link) {
        List<LinksTagsRecord> linkTagList = context.selectFrom(Tables.LINKS_TAGS)
        .where(Tables.LINKS_TAGS.LINK_ID.eq(link.getId()))
        .fetch();

        List<Long> linkTagIds = link.getTags().stream().map(Tag::getId).collect(Collectors.toList());

        linkTagList.forEach(tag -> {
            if (!linkTagIds.contains(tag.getTagId())) {
                context.delete(Tables.LINKS_TAGS)
                    .where(Tables.LINKS_TAGS.ID.eq(tag.getId()))
                    .execute();
            };
        });

        List<Long> linkTagIdsFromBd = linkTagList.stream().map(LinksTagsRecord::getTagId).collect(Collectors.toList());

        linkTagIds.forEach(tag -> {
            if (!linkTagIdsFromBd.contains(tag)) {
                context.insertInto(Tables.LINKS_TAGS, Tables.LINKS_TAGS.LINK_ID, Tables.LINKS_TAGS.TAG_ID)
                    .values(link.getId(), tag)
                    .execute();
            }
        });
    }
}