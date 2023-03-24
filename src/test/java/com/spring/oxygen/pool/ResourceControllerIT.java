package com.spring.oxygen.pool;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.spring.oxygen.BaseIntegrationTest;
import com.spring.oxygen.pool.contract.PoolSaveRequest;
import com.spring.oxygen.pool.service.ResourceService;

public class ResourceControllerIT extends BaseIntegrationTest {
	private static final String POOL_PATH = "pool/v1/update";
	@Autowired
	private ResourceService svc;
	
	@BeforeEach
	void setup() {
		
	}
	
    @Test
    void appendPoolValues() throws Exception {
    	PoolSaveRequest request = new PoolSaveRequest();
    	request.setPoolId(123);
    	request.setPoolValues(new int[] {8, 6, 9});
        getMockMvc().perform(MockMvcRequestBuilders
        		.post(getUrl(POOL_PATH))
        		.content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.status", is("appended")))
                .andExpect(status().is2xxSuccessful());
    }
}
