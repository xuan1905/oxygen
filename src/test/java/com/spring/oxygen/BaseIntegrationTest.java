package com.spring.oxygen;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BaseIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    public static final String URL = "http://localhost:8080/";
    
    public ObjectMapper objectMapper = new ObjectMapper();
    
    public String getUrl(String path) {
    	return URL + path;
    }
    
    public MockMvc getMockMvc() {
    	return this.mockMvc;
    }
    
}
