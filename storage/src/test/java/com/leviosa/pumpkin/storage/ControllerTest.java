package com.leviosa.pumpkin.storage;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import java.io.IOException;

import com.leviosa.pumpkin.storage.controller.Controller;
import com.leviosa.pumpkin.storage.domain.Tag;
import com.leviosa.pumpkin.storage.facade.LinksFacade;
import com.leviosa.pumpkin.storage.facade.TagsFacade;
import com.leviosa.pumpkin.storage.repository.LinksRepository;
import com.leviosa.pumpkin.storage.repository.LinksTagsRepository;
import com.leviosa.pumpkin.storage.repository.TagsRepository;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(Controller.class)
@AutoConfigureJsonTesters
@ExtendWith(SpringExtension.class)
public class ControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<Tag> jsonTagRequest;
    @Autowired
    private JacksonTester<Tag> jsonTagResponse;
    @MockBean
    private TagsRepository tagsRepository;
    @MockBean
    private LinksRepository linksRepository;
    @MockBean
    private LinksTagsRepository linksTagsRepository;
    @MockBean
    private TagsFacade tagsFacade;
    @MockBean
    private LinksFacade linksFacade;

    @Test
	public void testPostTag() throws IOException, Exception {
        Tag tag = new Tag(1, RandomStringUtils.randomAlphabetic(6));
        
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.post("/storage/tags")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTagRequest.write(tag).getJson()))
            .andReturn()
            .getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
    
    @Test
    public void testGetTag() throws Exception {
        long id = 1;
        Tag expectedResponse = new Tag(id, 2, "test");
        given(tagsFacade.getTag(id)).willReturn(expectedResponse);

        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.get("/storage/tags/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(jsonTagResponse.write(expectedResponse).getJson());
    }
 }