package com.burderly.topranking;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.burderly.topranking.entity.Score;
import com.burderly.topranking.repository.ScoreRepository;
import com.burderly.topranking.services.ScoreService;



@SpringBootTest
public class ScoreServiceTest {
	
	@Autowired
	private ScoreService scoreService;
	
	@MockBean
	private ScoreRepository scoreRepository;
	
	
    @Test
    @DisplayName("Test getPlayerScore Success")
    void testgetPlayerScoreSuccess() throws Exception {
    	
		Score scoreInput = new Score();
	    scoreInput.setPlayer("UserTest001");
	    scoreInput.setScore(80);
	    scoreInput.setId(66l);
	    Date dateInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
	    scoreInput.setTime(dateInput);
	    
	    Mockito.doReturn(Optional.of(scoreInput)).when(scoreRepository).findById(66l);
        Optional<Score> scoreReturn = scoreService.findById(66l);
        
        Assertions.assertTrue(scoreReturn.isPresent(), "scoreReturn was found");
        Assertions.assertSame(scoreInput, scoreReturn.orElse(null), " Id is the same and Object should be the same");
    
    }
	
    @Test
    @DisplayName("Test getPlayerScore Failed")
    void testgetPlayerScoreFailed() throws Exception {
    	
		Score scoreInput = new Score();
	    scoreInput.setPlayer("UserTest001");
	    scoreInput.setScore(80);
	    scoreInput.setId(76l);
	    Date dateInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
	    scoreInput.setTime(dateInput);
	    
	    Mockito.doReturn(Optional.of(scoreInput)).when(scoreRepository).findById(76l);
        Optional<Score> scoreReturn = scoreService.findById(66l);
        
        Assertions.assertTrue(scoreReturn.isEmpty(), "scoreReturn was found");
        Assertions.assertNotSame(scoreInput, scoreReturn.orElse(null), " Id is different and Object should NOT be the same");
    
    }	
	
    @Test
    @DisplayName("Test savePlayerScore Success")
    void testsavePlayerScoreSucess() throws Exception {
    	
		Score scoreInput = new Score();
	    scoreInput.setPlayer("UserTest001");
	    scoreInput.setScore(80);
	    scoreInput.setId(86l);
	    Date dateInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
	    scoreInput.setTime(dateInput);
	    
	    Mockito.doReturn(scoreInput).when(scoreRepository).save(scoreInput);
        Score scoreReturn = scoreService.save(scoreInput);
        
        Assertions.assertTrue(scoreReturn != null, "scoreReturn was found");
        Assertions.assertSame(scoreInput, scoreReturn, " Object saved before and after should be the same");
    
    }	
}
