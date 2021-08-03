package com.leviosa.pumpkin.storage;

import java.util.List;

import com.leviosa.pumpkin.storage.domain.Link;
import com.leviosa.pumpkin.storage.domain.Tag;
import com.leviosa.pumpkin.storage.repository.LinksRepository;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LinksRepositoryTest {
    @Autowired
    private LinksRepository linksRepository;

    @Test
    public void testCreateUpdateLink() {
        int userId = 1;
        Link link = new Link(
            RandomStringUtils.randomAlphabetic(6),
            RandomStringUtils.randomAlphabetic(10),
            userId,
            List.of(
                new Tag(1, userId, "fashion"),
                new Tag(2, userId, "food")
            )); 
        long id = linksRepository.create(link);
        link.setId(id);

        List<Link> links = linksRepository.getLinksById(id);
        Assert.assertEquals(1, links.size());
        Link linkFromDb = links.get(0);
        Assert.assertEquals(link, linkFromDb);

        link.setLink(RandomStringUtils.randomAlphabetic(6));
        link.setDescription(RandomStringUtils.randomAlphabetic(10));
        link.getTags().set(1, new Tag(3, userId, "sport"));
        linksRepository.update(link);

        links = linksRepository.getLinksById(id);
        Assert.assertEquals(1, links.size());
        linkFromDb = links.get(0);
        Assert.assertEquals(link, linkFromDb);
    }
}