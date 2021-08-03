package com.leviosa.pumpkin.storage.repository;

import java.util.List;
import java.util.stream.Collectors;

import com.leviosa.pumpkin.db.gen.Tables;
import com.leviosa.pumpkin.db.gen.tables.records.LinksRecord;
import com.leviosa.pumpkin.db.gen.tables.records.LinksTagsRecord;
import com.leviosa.pumpkin.storage.domain.Link;
import com.leviosa.pumpkin.storage.domain.Tag;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LinksRepository {
    @Autowired
    DSLContext context;
    @Autowired
    private TagsRepository tagsRepository;

    public List<Link> getLinksByUser(int userId) {
        List<LinksRecord> linkRecords = context.selectFrom(Tables.LINKS)
            .where(Tables.LINKS.USER_ID.eq(userId))
            .fetch();
        
        return fetchLinks(linkRecords);
    }

    public List<Link> getLinksByUserAndTag(int userId, long tagId) {
        List<LinksRecord> linkRecords = context.select()
            .from(Tables.LINKS)
            .join(Tables.LINKS_TAGS)
            .on(Tables.LINKS.LINK_ID.eq(Tables.LINKS_TAGS.LINK_ID))
            .where(Tables.LINKS.USER_ID.eq(userId))
            .and(Tables.LINKS_TAGS.TAG_ID.eq(tagId))
            .fetch()
            .into(LinksRecord.class);
        
        return fetchLinks(linkRecords);
    }

    public List<Link> getLinksById(long linkId) {
        List<LinksRecord> linkRecords = context.selectFrom(Tables.LINKS)
            .where(Tables.LINKS.LINK_ID.eq(linkId))
            .fetch();
            
        return fetchLinks(linkRecords);
    }

    public long create(Link link) {
        long id = context.insertInto(Tables.LINKS, Tables.LINKS.LINK_NAME, Tables.LINKS.DESCRIPTION, Tables.LINKS.USER_ID) 
            .values(link.getLink(), link.getDescription(), link.getUserId())
            .returningResult(Tables.LINKS.LINK_ID)
            .fetchOne()
            .getValue(Tables.LINKS.LINK_ID);

        link.getTags().forEach(tag -> {
            context.insertInto(Tables.LINKS_TAGS, Tables.LINKS_TAGS.LINK_ID, Tables.LINKS_TAGS.TAG_ID)
                .values(id, tag.getId())
                .execute();
        });

        return id;
    }

    public void update(Link link) {
        context.update(Tables.LINKS)
            .set(Tables.LINKS.LINK_NAME, link.getLink())
            .set(Tables.LINKS.DESCRIPTION, link.getDescription())
            .where(Tables.LINKS.LINK_ID.eq(link.getId()))
            .execute();

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

    private List<Link> fetchLinks(List<LinksRecord> records) {
        return records.stream()
        .map(record -> {
            Link link = new Link();
            link.setId(record.getLinkId());
            link.setLink(record.getLinkName());
            link.setDescription(record.getDescription());
            link.setUserId(record.getUserId());
            link.setTags(tagsRepository.getTagsByLinkId(link.getId()));
            return link;
        }).collect(Collectors.toList());
    }
}