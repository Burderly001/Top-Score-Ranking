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
public class ScoresControllerTest {
	
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScoreService scoreService;
    
    final private String errorStr = "{\"code\":500,\"error\":\"System error\"}";
	
	@Test
	@DisplayName("Test getPlayer Scores from controller")
	public void addPlayerScore() throws Exception {
		
		// Parameters are correct
        this.mvc.perform(MockMvcRequestBuilders.post("/api/scores/player/userTest003"))
        	.andExpect(MockMvcResultMatchers.status().isOk());
        
        // Missing player name field
        this.mvc.perform(MockMvcRequestBuilders.post("/api/scores/player/"))
        	.andExpect(MockMvcResultMatchers.status().is4xxClientError());
        
        // With paging
        Object result = this.mvc.perform(MockMvcRequestBuilders.post("/api/scores/player/Test001?page=1&pagesize=4"))
       
        	.andExpect(MockMvcResultMatchers.status().isOk())
        	.andReturn().getResponse().getContentAsString();
       
        Assertions.assertTrue(result.equals(this.errorStr)); 
        
        // Missing one page parameter
	    this.mvc.perform(MockMvcRequestBuilders.post("/api/scores/Test002?pagesize=3"))
	   
	    	.andExpect(MockMvcResultMatchers.status().is4xxClientError());
  
	}
	
	
	@Test
	@DisplayName("Test getPlayer Scores with specific date from controller")
	public void addPlayersScore() throws Exception {
		
		// Parameters are correct
        this.mvc.perform(MockMvcRequestBuilders.post("/api/scores/players?players=testuser35,Testuser38&startdate=2019-04-03 13:31:35&enddate=2021-04-13 13:00:00&page=1&pagesize=10"))
        	.andExpect(MockMvcResultMatchers.status().isOk());
        
		// Parameters are correct
        this.mvc.perform(MockMvcRequestBuilders.post("/api/scores/players?players=testuser35,Testuser38&startdate=2019-04-03 13:31:35&enddate=2021-04-13 13:00:00"))
        	.andExpect(MockMvcResultMatchers.status().isOk());
        
		// Parameters are correct
        this.mvc.perform(MockMvcRequestBuilders.post("/api/scores/players?players=testuser35"))
        	.andExpect(MockMvcResultMatchers.status().isOk());
        
		// Parameters are correct
        this.mvc.perform(MockMvcRequestBuilders.post("/api/scores/players?startdate=2019-04-03 13:31:35"))
        	.andExpect(MockMvcResultMatchers.status().isOk());
        
		// Parameters are correct
        this.mvc.perform(MockMvcRequestBuilders.post("/api/scores/players?startdate=2019-04-03 13:31:35&page=1&pagesize=10"))
        	.andExpect(MockMvcResultMatchers.status().isOk());
        
		// Parameter are correct
        this.mvc.perform(MockMvcRequestBuilders.post("/api/scores/players"))
        	.andExpect(MockMvcResultMatchers.status().isOk());
        
		// URI incorrect
        this.mvc.perform(MockMvcRequestBuilders.post("/api/scores/players123"))
        	.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

}

