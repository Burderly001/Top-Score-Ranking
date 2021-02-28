package com.burderly.topranking;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class ScoreIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ScoreService scoreService;

	@Test
	@DisplayName("Integration test addPlayer Score from controller")
	void addPlayerScore() throws Exception {

		Score scoreInput = new Score();
		scoreInput.setPlayer("UserTest001");
		scoreInput.setScore(80);
		Date dateInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
		scoreInput.setTime(dateInput);

		Mockito.when(scoreService.save(scoreInput)).thenReturn(scoreInput);

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/api/score/add?UserTest001=abc&score=80&time=2021-02-03 12:31:02")
						.contentType("application/json")

						.content(objectMapper.writeValueAsString(scoreInput)))
				.andExpect(MockMvcResultMatchers.status().isOk());

		Score scoreEntity = scoreService.save(scoreInput);
		Assertions.assertTrue(scoreInput.equals(scoreEntity));
	}

	@Test
	@DisplayName("Integration test getPlayer Score from controller")
	void getPlayerScore() throws Exception {

		Score scoreInput = new Score();
		scoreInput.setPlayer("UserTest001");
		scoreInput.setScore(80);
		Date dateInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
		scoreInput.setTime(dateInput);

		Optional<Score> newData = Optional.ofNullable(scoreInput);

		Mockito.when(scoreService.findById(100l)).thenReturn(newData);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/score/id/100")

				.content(objectMapper.writeValueAsString(scoreInput))).andExpect(MockMvcResultMatchers.status().isOk());

		Score scoreEntity = scoreService.findById(100l).orElse(null);
		Assertions.assertTrue(scoreInput.equals(scoreEntity));
	}

	@Test
	@DisplayName("Integration test delPlayer Score from controller")
	void delPlayerScore() throws Exception {

		Score scoreInput = new Score();
		scoreInput.setPlayer("UserTest001");
		scoreInput.setScore(80);
		Date dateInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-02-03 12:31:02");
		scoreInput.setTime(dateInput);

		Mockito.verify(scoreService);
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/score/id/100")

				.content(objectMapper.writeValueAsString(scoreInput))).andExpect(MockMvcResultMatchers.status().isOk());

		scoreService.deleteById(100l);
	}

}
