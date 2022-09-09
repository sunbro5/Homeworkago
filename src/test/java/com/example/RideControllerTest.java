package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class RideControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void testTest() throws Exception {
        MvcResult result = mvc.perform(get("/rides?offset=0&limit=20")).andExpect(status().isOk()).andReturn();
        assertEquals("fail", result.getResponse().getContentAsString());
    }

}