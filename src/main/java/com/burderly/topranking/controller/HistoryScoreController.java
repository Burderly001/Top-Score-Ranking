package com.burderly.topranking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.burderly.topranking.entity.ResponseJson;
import com.burderly.topranking.entity.Score;
import com.burderly.topranking.services.ScoreService;

@RestController
@RequestMapping("api/history")
public class HistoryScoreController {
	
	@Autowired
	ScoreService scoreService;
	
	
	@GetMapping("/top/player/{player}")
	public ResponseJson getTopScore(@PathVariable String player) {
		
		List <Score> playersJson = scoreService.findAllByPlayerTop(player.toLowerCase());
		
		ResponseJson json = new ResponseJson();
	    json.setCode("0000");
	    json.setMessage(playersJson);
		
		return json;
		
	}
	
	
	@GetMapping("/low/player/{player}")
	public ResponseJson getLowScore(@PathVariable String player) {
		
		List <Score> playersJson = scoreService.findAllByPlayerLow(player.toLowerCase());
		
		ResponseJson json = new ResponseJson();
	    json.setCode("0000");
	    json.setMessage(playersJson);
		
		return json;
		
	}
	
	@GetMapping("/avg/player/{player}")
	public ResponseJson getAvgScore(@PathVariable String player) {
		
		String playersJson = scoreService.findAllByPlayerAvg(player.toLowerCase());
		
		ResponseJson json = new ResponseJson();
	    json.setCode("0000");
	    json.setMessage("Average score: " + playersJson);
		
		return json;
		
	}
	
	
	@GetMapping("/player/{player}")
	public ResponseJson getPlayerScore(@PathVariable String player) {
		
		List <Score> playersJson = scoreService.findAllByPlayer(player.toLowerCase());
		
		ResponseJson json = new ResponseJson();
	    json.setCode("0000");
	    json.setMessage(playersJson);
		
		return json;
		
	}
	

}
