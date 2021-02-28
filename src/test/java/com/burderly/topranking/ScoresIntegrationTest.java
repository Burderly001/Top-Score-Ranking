package com.burderly.topranking;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.burderly.topranking.entity.Score;
import com.burderly.topranking.services.ScoreService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ScoresIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ScoreService scoreService;

	@Test
	@DisplayName("Integration test getPlayer Score from controller")
	void getPlayerScore() throws Exception {

		Score scoreInput = new Score();
		scoreInput.setPlayer("UserTest001");
		scoreInput.setScore(80);
		Date dateInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
		scoreInput.setTime(dateInput);

		List<Score> newData = new ArrayList<Score>();
		newData.add(scoreInput);

		Mockito.when(scoreService.findAllByPlayer("UserTest001")).thenReturn(newData);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/scores/player/tesT001")

				.content(objectMapper.writeValueAsString(newData))).andExpect(MockMvcResultMatchers.status().isOk());

		List<Score> scoreEntity = scoreService.findAllByPlayer("UserTest001");
		Assertions.assertTrue(newData.equals(scoreEntity));
	}

	@Test
	@DisplayName("Integration test getPlayer Paging Score from controller")
	void getPlayerScorePaging() throws Exception {

		Score scoreInput = new Score();
		scoreInput.setPlayer("UserTest001");
		scoreInput.setScore(80);
		Date dateInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
		scoreInput.setTime(dateInput);

		Score scoreInput1 = new Score();
		scoreInput1.setPlayer("UserTest001");
		scoreInput1.setScore(100);
		Date dateInput1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
		scoreInput.setTime(dateInput1);

		List<Score> newData = new ArrayList<Score>();
		newData.add(scoreInput);
		newData.add(scoreInput1);

		Mockito.when(scoreService.findAllByPlayer("UserTest001")).thenReturn(newData);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/scores/player/tesT001?page=1&pagesize=10")

				.content(objectMapper.writeValueAsString(newData))).andExpect(MockMvcResultMatchers.status().isOk());

		List<Score> scoreEntity = scoreService.findAllByPlayer("UserTest001");
		Assertions.assertTrue(newData.equals(scoreEntity));
	}

	@Test
	@DisplayName("Integration test getPlayers Score from controller")
	void getPlayersScore() throws Exception {

		Score scoreInput = new Score();
		scoreInput.setPlayer("UserTest001");
		scoreInput.setScore(80);
		Date dateInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
		scoreInput.setTime(dateInput);

		Score scoreInput1 = new Score();
		scoreInput1.setPlayer("UserTest002");
		scoreInput1.setScore(100);
		Date dateInput1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
		scoreInput.setTime(dateInput1);

		List<Score> newData = new ArrayList<Score>();
		newData.add(scoreInput);
		newData.add(scoreInput1);

		List<String> players = Arrays.asList("UserTest001", "UserTest002");

		List<String> playersList = null;
		if (players != null && players.size() >= 1) { // has players
			playersList = players;
			ListIterator<String> iterator = playersList.listIterator();
			while (iterator.hasNext()) {
				iterator.set(iterator.next().toLowerCase());
			}
		}

		Mockito.when(scoreService.findAllByPlayersandStart(playersList, dateInput1)).thenReturn(newData);

		this.mockMvc
				.perform(MockMvcRequestBuilders
						.get("/api/scores/players?players=UserTest001,UserTest002&start=" + "2021-02-03 12:31:02")

						.content(objectMapper.writeValueAsString(newData)))
				.andExpect(MockMvcResultMatchers.status().isOk());

		List<Score> scoreEntity = scoreService.findAllByPlayersandStart(playersList, dateInput1);
		Assertions.assertTrue(newData.equals(scoreEntity));
	}

	@Test
	@DisplayName("Integration test getPlayers Paging Score from controller")
	void getPlayersScorePaging() throws Exception {

		Score scoreInput = new Score();
		scoreInput.setPlayer("UserTest001");
		scoreInput.setScore(80);
		Date dateInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
		scoreInput.setTime(dateInput);

		Score scoreInput1 = new Score();
		scoreInput1.setPlayer("UserTest002");
		scoreInput1.setScore(100);
		Date dateInput1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
		scoreInput.setTime(dateInput1);

		List<Score> newData = new ArrayList<Score>();
		newData.add(scoreInput);
		newData.add(scoreInput1);

		List<String> players = Arrays.asList("UserTest001", "UserTest002");
		List<String> playersList = null;
		if (players != null && players.size() >= 1) { // has players
			playersList = players;
			ListIterator<String> iterator = playersList.listIterator();
			while (iterator.hasNext()) {
				iterator.set(iterator.next().toLowerCase());
			}
		}

		Pageable paging = PageRequest.of(0, 10); // Paging

		Mockito.when(scoreService.findAllByPlayersandStartPage(playersList, dateInput1, paging)).thenReturn(newData);

		this.mockMvc
				.perform(MockMvcRequestBuilders
						.get("/api/scores/players?players=UserTest001,UserTest002&start="
								+ "2021-02-03 12:31:02&page=1&pagesize=10")

						.content(objectMapper.writeValueAsString(newData)))
				.andExpect(MockMvcResultMatchers.status().isOk());

		List<Score> scoreEntity = scoreService.findAllByPlayersandStartPage(playersList, dateInput1, paging);
		Assertions.assertTrue(newData.equals(scoreEntity));
	}

	// by end date
	@Test
	@DisplayName("Integration test getPlayers Score from controller")
	void getPlayersScorewithEnd() throws Exception {

		Score scoreInput = new Score();
		scoreInput.setPlayer("UserTest001");
		scoreInput.setScore(80);
		Date dateInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
		scoreInput.setTime(dateInput);

		Score scoreInput1 = new Score();
		scoreInput1.setPlayer("UserTest002");
		scoreInput1.setScore(100);
		Date dateInput1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
		scoreInput.setTime(dateInput1);

		List<Score> newData = new ArrayList<Score>();
		newData.add(scoreInput);
		newData.add(scoreInput1);

		List<String> players = Arrays.asList("UserTest001", "UserTest002");

		List<String> playersList = null;
		if (players != null && players.size() >= 1) { // has players
			playersList = players;
			ListIterator<String> iterator = playersList.listIterator();
			while (iterator.hasNext()) {
				iterator.set(iterator.next().toLowerCase());
			}
		}

		Mockito.when(scoreService.findAllByPlayersandEnd(playersList, dateInput1)).thenReturn(newData);

		this.mockMvc
				.perform(MockMvcRequestBuilders
						.get("/api/scores/players?players=UserTest001,UserTest002&end=" + "2021-02-03 12:31:02")

						.content(objectMapper.writeValueAsString(newData)))
				.andExpect(MockMvcResultMatchers.status().isOk());

		List<Score> scoreEntity = scoreService.findAllByPlayersandEnd(playersList, dateInput1);
		Assertions.assertTrue(newData.equals(scoreEntity));
	}

	@Test
	@DisplayName("Integration test getPlayers Paging Score from controller")
	void getPlayersScorewithEndPaging() throws Exception {

		Score scoreInput = new Score();
		scoreInput.setPlayer("UserTest001");
		scoreInput.setScore(80);
		Date dateInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
		scoreInput.setTime(dateInput);

		Score scoreInput1 = new Score();
		scoreInput1.setPlayer("UserTest002");
		scoreInput1.setScore(100);
		Date dateInput1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
		scoreInput.setTime(dateInput1);

		List<Score> newData = new ArrayList<Score>();
		newData.add(scoreInput);
		newData.add(scoreInput1);

		List<String> players = Arrays.asList("UserTest001", "UserTest002");
		List<String> playersList = null;
		if (players != null && players.size() >= 1) { // has players
			playersList = players;
			ListIterator<String> iterator = playersList.listIterator();
			while (iterator.hasNext()) {
				iterator.set(iterator.next().toLowerCase());
			}
		}

		Pageable paging = PageRequest.of(0, 10); // Paging

		Mockito.when(scoreService.findAllByPlayersandEndPage(playersList, dateInput1, paging)).thenReturn(newData);

		this.mockMvc
				.perform(MockMvcRequestBuilders
						.get("/api/scores/players?players=UserTest001,UserTest002&end="
								+ "2021-02-03 12:31:02&page=1&pagesize=10")

						.content(objectMapper.writeValueAsString(newData)))
				.andExpect(MockMvcResultMatchers.status().isOk());

		List<Score> scoreEntity = scoreService.findAllByPlayersandEndPage(playersList, dateInput1, paging);
		Assertions.assertTrue(newData.equals(scoreEntity));
	}

}
