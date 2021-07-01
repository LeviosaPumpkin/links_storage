package com.leviosa.pumpkin.storage.controller;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

import com.leviosa.pumpkin.storage.domain.Tag;
import com.leviosa.pumpkin.storage.repository.TagsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "storage")
public class Controller {
    @Autowired
    private TagsRepository tagsRepository;

    @GetMapping(value="{userId}/links")
    public void getLinks(@PathVariable("userId") int userId, @RequestParam(required=false) MultiValueMap parameters) {
        if (parameters.isEmpty()) {
            System.out.println("Get all links for user " + userId);
        } else if (parameters.get("tagId") != null) {
            System.out.println("Request from user " + userId + " and tags " + parameters.get("tagId"));
        } else if (parameters.get("linkId") != null) {
            System.out.println("Request from user " + userId + " and linkId " + parameters.get("linkId"));
        } else {
            System.out.println("NO suitable requests");
        } 
    } 

    @GetMapping(value="/tags/{tagId}")
    public Tag getTag(@PathVariable("tagId") int tagId) {
        return tagsRepository.getTag(tagId);
    }
}