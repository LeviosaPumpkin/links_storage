package com.leviosa.pumpkin.storage.facade;

import java.util.List;

import com.leviosa.pumpkin.storage.domain.Tag;
import com.leviosa.pumpkin.storage.repository.TagsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagsFacade {
    @Autowired
    private TagsRepository tagsRepository;
    
    public Tag getTag(long id) {
        return tagsRepository.getTag(id);
    }

    public List<Tag> getTagsByLinkId(long linkId) {
        return tagsRepository.getTagsByLinkId(linkId);
    }

    @Transactional
    public long create(Tag tag) { 
        return tagsRepository.create(tag);
    }

    @Transactional
    public void update(Tag tag) {
        tagsRepository.update(tag);
    }
}