package com.leviosa.pumpkin.storage.facade;

import java.util.List;

import com.leviosa.pumpkin.storage.domain.Link;
import com.leviosa.pumpkin.storage.repository.LinksRepository;
import com.leviosa.pumpkin.storage.repository.LinksTagsRepository;
import com.leviosa.pumpkin.storage.repository.TagsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LinksFacade {
    @Autowired
    private LinksRepository linksRepository;
    @Autowired 
    private TagsRepository tagsRepository;
    @Autowired
    private LinksTagsRepository linksTagsRepository;

    public List<Link> getLinksByUser(int userId) {
        return linksRepository.getLinksByUser(userId);
    }

    public List<Link> getLinksByUserAndTag(int userId, long tagId) {
        return linksRepository.getLinksByUserAndTag(userId, tagId);
    }

    public List<Link> getLinksById(long linkId) {
        return linksRepository.getLinksById(linkId);
    }

    @Transactional
    public long create(Link link) {
        long id = linksRepository.create(link);
        linksTagsRepository.create(link, id);
        return id;
    }
    
    @Transactional
    public void update(Link link) {
        linksRepository.update(link);
        linksTagsRepository.update(link);
    }

}