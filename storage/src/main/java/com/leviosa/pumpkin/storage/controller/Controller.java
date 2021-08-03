package com.leviosa.pumpkin.storage.controller;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import com.leviosa.pumpkin.storage.domain.Link;
import com.leviosa.pumpkin.storage.domain.Tag;
import com.leviosa.pumpkin.storage.repository.LinksRepository;
import com.leviosa.pumpkin.storage.repository.TagsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(value = "storage")
public class Controller {
    @Autowired
    private TagsRepository tagsRepository;
    @Autowired
    private LinksRepository linksRepository;

    @GetMapping(value="{userId}/links")
    public List<Link> getLinks(@PathVariable("userId") int userId, @RequestParam(required=false) MultiValueMap parameters) {
        if (parameters.isEmpty()) {
            System.out.println("Get all links for user " + userId);
            return linksRepository.getLinksByUser(userId);
        } else if (parameters.get("tagId") != null) {
            System.out.println("Request from user " + userId + " and tags " + parameters.get("tagId"));
            return linksRepository.getLinksByUserAndTag(userId, Long.parseLong(String.valueOf(parameters.get("tagId"))));
        } else if (parameters.get("linkId") != null) {
            System.out.println("Request from user " + userId + " and linkId " + parameters.get("linkId"));
            return linksRepository.getLinksById(Long.parseLong(String.valueOf(parameters.get("linkId"))));
        } else {
            System.out.println("NO suitable requests");
            return null;
        } 
    } 

    @PostMapping(path = "/links", consumes = "application/json", produces = "application/json")
    public void createLink(@RequestBody Link link) {
        linksRepository.create(link);
    }

    @PutMapping(path = "/links", consumes = "application/json", produces = "application/json")
    public void updateLink(@RequestBody Link link) {
        linksRepository.update(link);
    }

    @GetMapping(value="/tags/{tagId}")
    public Tag getTag(@PathVariable("tagId") long tagId) {
        return tagsRepository.getTag(tagId);
    }  
    
    @PostMapping(path = "/tags", consumes = "application/json", produces = "application/json")
    public void createTag(@RequestBody Tag tag) {
        tagsRepository.create(tag);
    }

    @PutMapping(path = "/tags", consumes = "application/json", produces = "application/json")
    public void updateTag(@RequestBody Tag tag) {
        tagsRepository.update(tag);
    }
}