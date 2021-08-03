package com.leviosa.pumpkin.storage;

import com.leviosa.pumpkin.storage.domain.Tag;
import com.leviosa.pumpkin.storage.facade.TagsFacade;
import com.leviosa.pumpkin.storage.repository.TagsRepository;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TagsFacadeTest {
    @Autowired
    private TagsFacade tagsFacade;

    @Test
    public void testCreateUpdateTag() {
        Tag tag = new Tag (1, RandomStringUtils.randomAlphabetic(6));
        long id = tagsFacade.create(tag);
        tag.setId(id);

        Assert.assertEquals(tag, tagsFacade.getTag(id));

        tag.setName(RandomStringUtils.randomAlphabetic(6));
        tagsFacade.update(tag);

        Assert.assertEquals(tag, tagsFacade.getTag(id));
    }
}