package com.burderly.topranking;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.burderly.topranking.entity.Score;
import com.burderly.topranking.repository.ScoreRepository;
import com.burderly.topranking.services.ScoreService;

@SpringBootTest
public class ScoresServiceTest {
	
	@Autowired
	private ScoreService scoreService;
	
	@MockBean
	private ScoreRepository scoreRepository;
	
	    @Test
	    @DisplayName("Test getPlayerScores by name Success")
	    void getPlayerScoresSuccess() throws Exception {
		  
		  
			Score scoreInput1 = new Score();
		    scoreInput1.setPlayer("UserTest001");
		    scoreInput1.setScore(80);
		    scoreInput1.setId(76l);
		    Date dateInput1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
		    scoreInput1.setTime(dateInput1);
		    
			Score scoreInput2 = new Score();
		    scoreInput2.setPlayer("userTest001");
		    scoreInput2.setScore(80);
		    scoreInput2.setId(66l);
		    Date dateInput2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-01-03 12:31:02");
		    scoreInput1.setTime(dateInput2);
		    
		    
			Score scoreInput3 = new Score();
		    scoreInput3.setPlayer("usertest001");
		    scoreInput3.setScore(20);
		    scoreInput3.setId(86l);
		    Date dateInput3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-03-03 12:31:02");
		    scoreInput1.setTime(dateInput3);
		    
		    
		    List<Score> expectedScores = new ArrayList<Score>();
		    expectedScores.add(scoreInput1);
		    expectedScores.add(scoreInput2);
		    expectedScores.add(scoreInput3);
		    
		    
		    Mockito.doReturn(expectedScores).when(scoreRepository).findAllByPlayerIgnoreCase("UserTest001");
		    List<Score> scoresReturn = scoreService.findAllByPlayer("UserTest001");
		    
		    Assertions.assertTrue(scoresReturn.size() == 3, "3 records was be found");
		    Assertions.assertSame(expectedScores, scoresReturn, " 3 records was be found"); 
		  
	  }
	    
	    @Test
	    @DisplayName("Test getPlayerScores Paging by name Success")
	    void getPlayerScoresPagingSuccess() throws Exception {
		  
		  
			Score scoreInput1 = new Score();
		    scoreInput1.setPlayer("UserTest001");
		    scoreInput1.setScore(80);
		    scoreInput1.setId(76l);
		    Date dateInput1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
		    scoreInput1.setTime(dateInput1);
		    
			Score scoreInput2 = new Score();
		    scoreInput2.setPlayer("userTest001");
		    scoreInput2.setScore(80);
		    scoreInput2.setId(66l);
		    Date dateInput2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-01-03 12:31:02");
		    scoreInput1.setTime(dateInput2);
		    
		    
			Score scoreInput3 = new Score();
		    scoreInput3.setPlayer("usertest001");
		    scoreInput3.setScore(20);
		    scoreInput3.setId(86l);
		    Date dateInput3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-03-03 12:31:02");
		    scoreInput1.setTime(dateInput3);
		    
		    
		    List<Score> expectedScores = new ArrayList<Score>();
		    expectedScores.add(scoreInput1);
		    expectedScores.add(scoreInput2);
		    expectedScores.add(scoreInput3);
		    
		    Pageable paging = PageRequest.of(0, 3); //Paging
		    Mockito.doReturn(expectedScores).when(scoreRepository).findAllByPlayerIgnoreCase("uSerTest001", paging);
		    List<Score> scoresReturn = scoreService.findAllByPlayer("uSerTest001", paging);
		    
		    Assertions.assertTrue(scoresReturn.size() == 3, "3 records was be found");
		    Assertions.assertSame(expectedScores, scoresReturn, " 3 records was be found"); 
		  
	  }
	    
	    @Test
	    @DisplayName("Test findAllByPlayersandStart by name Success")
	    void findAllByPlayersandStartSuccess() throws Exception {
		  
		  
			Score scoreInput1 = new Score();
		    scoreInput1.setPlayer("UserTest001");
		    scoreInput1.setScore(80);
		    scoreInput1.setId(76l);
		    Date dateInput1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
		    scoreInput1.setTime(dateInput1);
		    
			Score scoreInput2 = new Score();
		    scoreInput2.setPlayer("userTest001");
		    scoreInput2.setScore(80);
		    scoreInput2.setId(66l);
		    Date dateInput2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-01-03 12:31:02");
		    scoreInput1.setTime(dateInput2);
		   
		    
		    List<Score> expectedScores = new ArrayList<Score>();
		    expectedScores.add(scoreInput1);
		    expectedScores.add(scoreInput2);
		    
		  
		    List <String> playersList = new ArrayList<String>();
		    playersList.add("userTest001");
		    Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
		    
		    Mockito.doReturn(expectedScores).when(scoreRepository).findAllByPlayersandStart(playersList, start);
		    List<Score> scoresReturn = scoreService.findAllByPlayersandStart(playersList, start);
		    
		    Assertions.assertTrue(scoresReturn.size() == 2, "2 records was be found");
		    Assertions.assertSame(expectedScores, scoresReturn, " 2 records was be found"); 
		  
	  }
	    
	    @Test
	    @DisplayName("Test findAllByPlayersandStart Paging by name Success")
	    void findAllByPlayersandStartPagingSuccess() throws Exception {
		  
		  
			Score scoreInput1 = new Score();
		    scoreInput1.setPlayer("UserTest001");
		    scoreInput1.setScore(80);
		    scoreInput1.setId(76l);
		    Date dateInput1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
		    scoreInput1.setTime(dateInput1);
		    
			Score scoreInput2 = new Score();
		    scoreInput2.setPlayer("userTest001");
		    scoreInput2.setScore(80);
		    scoreInput2.setId(66l);
		    Date dateInput2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-01-03 12:31:02");
		    scoreInput1.setTime(dateInput2);
		   
		    
		    List<Score> expectedScores = new ArrayList<Score>();
		    expectedScores.add(scoreInput1);
		    expectedScores.add(scoreInput2);
		    
		  
		    List <String> playersList = new ArrayList<String>();
		    playersList.add("userTest001");
		    Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
		    
		    Pageable paging = PageRequest.of(0, 2); //Paging
		    Mockito.doReturn(expectedScores).when(scoreRepository).findAllByPlayersandStartPage(playersList, start, paging);
		    List<Score> scoresReturn = scoreService.findAllByPlayersandStartPage(playersList, start, paging);
		    
		    Assertions.assertTrue(scoresReturn.size() == 2, "2 records was be found");
		    Assertions.assertSame(expectedScores, scoresReturn, " 2 records was be found"); 
		  
	  }

}
