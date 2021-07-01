package com.leviosa.pumpkin.storage.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leviosa.pumpkin.storage.domain.Link;

import org.jooq.DSLContext;

@Repository
public class LinksRepository {
    @Autowired
    DSLContext context;
}