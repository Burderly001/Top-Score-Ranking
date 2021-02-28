package com.burderly.topranking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.burderly.topranking.services.ScoreService;


@WebMvcTest
public class ScoreControllerTest {
	
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScoreService scoreService;
    
    final private String error500 = "{\"code\":500,\"error\":\"System error\"}";
	
	@Test
	@DisplayName("Test addPlayer from controller")
	public void addPlayerScore() throws Exception {
		
		// Parameters are correct
        this.mvc.perform(MockMvcRequestBuilders.post("/api/score/add?name=abc&score=100&time=2021-02-03 12:31:02"))
        	.andExpect(MockMvcResultMatchers.status().isOk());
        
        // URI error with add123
        this.mvc.perform(MockMvcRequestBuilders.post("/api/score/add123?name=abc&score=100&time=2021-02-03 12:31:02"))
        	.andExpect(MockMvcResultMatchers.status().is4xxClientError());
        
        // Missing name field
        Object result = this.mvc.perform(MockMvcRequestBuilders.post("/api/score/add?score=100&time=2021-02-03 12:31:02"))
       
        	.andExpect(MockMvcResultMatchers.status().isOk())
        	.andReturn().getResponse().getContentAsString();
       
        Assertions.assertTrue(result.equals(this.error500)); 
        
        // Missing name and time fields
	    Object result1 = this.mvc.perform(MockMvcRequestBuilders.post("/api/score/add?score=100"))
	   
	    	.andExpect(MockMvcResultMatchers.status().isOk())
	    	.andReturn().getResponse().getContentAsString();
	    //System.out.println(result1);
	    Assertions.assertTrue(result1.equals(this.error500)); 
	    
        // Missing name, time and score fields
	    Object result2 = this.mvc.perform(MockMvcRequestBuilders.post("/api/score/add?score=100"))
	   
	    	.andExpect(MockMvcResultMatchers.status().isOk())
	    	.andReturn().getResponse().getContentAsString();
	    //System.out.println(result2);
	    Assertions.assertTrue(result2.equals(this.error500)); 
	}
	
	@Test
	@DisplayName("Test delPlayer from controller")
	public void delPlayerScore() throws Exception {
		// Parameters are correct
        this.mvc.perform(MockMvcRequestBuilders.delete("/api/score/id/userTest004"))
        	.andExpect(MockMvcResultMatchers.status().isOk());
        
        // Missing parameter
        this.mvc.perform(MockMvcRequestBuilders.delete("/api/score/id/"))
    	.andExpect(MockMvcResultMatchers.status().is4xxClientError());
        
	}
	
	@Test
	@DisplayName("Test getPlayer from controller")
	public void getPlayerScore() throws Exception {
		// Parameters are correct
        this.mvc.perform(MockMvcRequestBuilders.get("/api/score/id/userTest004"))
        	.andExpect(MockMvcResultMatchers.status().isOk());
        
        // Missing parameter
        this.mvc.perform(MockMvcRequestBuilders.get("/api/score/id/"))
    	.andExpect(MockMvcResultMatchers.status().is4xxClientError());
        
	}

}
