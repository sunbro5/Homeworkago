package com.example;

import com.example.comment.model.CommentAddRequest;
import com.example.comment.repository.CommentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(properties = {"logging.level.com.example=DEBUG"})
class RideControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CommentRepository commentRepository;

    private ObjectMapper mapper = new ObjectMapper();


    @AfterEach
    void after(){
        commentRepository.deleteAll();
    }

    @Test
    void testRides() throws Exception {
        MvcResult result = mvc.perform(get("/rides?offset=0&limit=20")).andExpect(status().isOk()).andReturn();
        String expectedJson = loadFileAsString("rides.json");
        assertEquals(expectedJson, result.getResponse().getContentAsString());
    }

    @Test
    void testRideDetail() throws Exception {
        MvcResult result = mvc.perform(get("/rides/3a026c93-d643-4c87-9aa9-e686eab93a20")).andExpect(status().isOk()).andReturn();
        String expectedJson = loadFileAsString("ridesDetail.json");
        assertEquals(expectedJson, result.getResponse().getContentAsString());
    }

    @Test
    void testRideComment() throws Exception {
        CommentAddRequest commentAddRequest = new CommentAddRequest("3a026c93-d643-4c87-9aa9-e686eab93a20", "halo");

        mvc.perform(post("/rides/comment").contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(commentAddRequest)))
                .andExpect(status().isOk());

        MvcResult result = mvc.perform(get("/rides/3a026c93-d643-4c87-9aa9-e686eab93a20")).andExpect(status().isOk()).andReturn();
        String expectedJson = loadFileAsString("ridesDetailComment.json");
        assertEquals(expectedJson, result.getResponse().getContentAsString());
    }

    private String loadFileAsString(String path){
        return asString(new ClassPathResource(path));
    }

    private String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}