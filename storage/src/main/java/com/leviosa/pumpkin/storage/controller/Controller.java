package com.leviosa.pumpkin.storage.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.leviosa.pumpkin.storage.domain.Link;
import com.leviosa.pumpkin.storage.domain.Tag;
import com.leviosa.pumpkin.storage.facade.LinksFacade;
import com.leviosa.pumpkin.storage.facade.TagsFacade;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "storage")
public class Controller {
    @Autowired
    private LinksFacade linksFacade;
    @Autowired
    private TagsFacade tagsFacade;

    @GetMapping(value="{userId}/links")
    public List<Link> getLinks(
            @PathVariable("userId") int userId, 
            @RequestParam(required=false) MultiValueMap parameters, 
            @RequestHeader HttpHeaders headers) {
        if (!isUserAuthorized(userId, Integer.parseInt(headers.getFirst("Username")))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        if (parameters.isEmpty()) {
            System.out.println("Get all links for user " + userId);
            return linksFacade.getLinksByUser(userId);
        } else if (parameters.get("tagId") != null) {
            System.out.println("Request from user " + userId + " and tags " + parameters.get("tagId"));
            return linksFacade.getLinksByUserAndTag(userId, Long.parseLong(String.valueOf(parameters.get("tagId"))));
        } else if (parameters.get("linkId") != null) {
            System.out.println("Request from user " + userId + " and linkId " + parameters.get("linkId"));
            return linksFacade.getLinksById(Long.parseLong(String.valueOf(parameters.get("linkId"))));
        } else {
            System.out.println("NO suitable requests");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } 
    } 

    @PostMapping(path = "{userId}/links", consumes = "application/json", produces = "application/json")
    public void createLink(
            @PathVariable("userId") int userId,
            @RequestBody Link link, 
            @RequestHeader HttpHeaders headers) {
        if (!isUserAuthorized(userId, Integer.parseInt(headers.getFirst("Username")))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        linksFacade.create(link);
    }

    @PutMapping(path = "{userId}/links", consumes = "application/json", produces = "application/json")
    public void updateLink(            
            @PathVariable("userId") int userId,
            @RequestBody Link link, 
            @RequestHeader HttpHeaders headers) {
        if (!isUserAuthorized(userId, Integer.parseInt(headers.getFirst("Username")))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        linksFacade.update(link);
    }

    @GetMapping(value="{userId}/tags/{tagId}")
    public Tag getTag(
            @PathVariable("userId") int userId,
            @PathVariable("tagId") long tagId, 
            @RequestHeader HttpHeaders headers) {
        if (!isUserAuthorized(userId, Integer.parseInt(headers.getFirst("Username")))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return tagsFacade.getTag(tagId);
    }  
    
    @PostMapping(path = "{userId}/tags", consumes = "application/json", produces = "application/json")
    public void createTag(
            @PathVariable("userId") int userId,
            @RequestBody Tag tag, 
            @RequestHeader HttpHeaders headers) {
        if (!isUserAuthorized(userId, Integer.parseInt(headers.getFirst("Username")))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        tagsFacade.create(tag);
    }

    @PutMapping(path = "{userId}/tags", consumes = "application/json", produces = "application/json")
    public void updateTag(
            @PathVariable("userId") int userId,
            @RequestBody Tag tag, 
            @RequestHeader HttpHeaders headers) {
        if (!isUserAuthorized(userId, Integer.parseInt(headers.getFirst("Username")))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        tagsFacade.update(tag);
    }

    private boolean isUserAuthorized(int userIdFromRequest, int userIdFromHeader) {
        return userIdFromHeader == userIdFromRequest;
    }
}