package com.leviosa.pumpkin.storage;

import com.leviosa.pumpkin.storage.domain.Tag;
import com.leviosa.pumpkin.storage.repository.TagsRepository;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TagsRepostoryTest {
    @Autowired
    private TagsRepository tagsRepository;

    @Test
    public void testCreateUpdateTag() {
        Tag tag = new Tag (1, RandomStringUtils.randomAlphabetic(6));
        long id = tagsRepository.create(tag);
        tag.setId(id);

        Assert.assertEquals(tag, tagsRepository.getTag(id));

        tag.setName(RandomStringUtils.randomAlphabetic(6));
        tagsRepository.update(tag);

        Assert.assertEquals(tag, tagsRepository.getTag(id));
    }
}