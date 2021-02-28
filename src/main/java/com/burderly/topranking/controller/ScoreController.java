package com.burderly.topranking.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.burderly.topranking.entity.ResponseJson;
import com.burderly.topranking.entity.Score;
import com.burderly.topranking.services.ScoreService;

//@Controller
@RestController
@RequestMapping("api/score")
public class ScoreController {
	
	@Autowired
	ScoreService scoreService;
	
	@PutMapping("/add")
	public ResponseJson addPlayerScore (@RequestParam String name,
		      @RequestParam int score, @RequestParam String time) throws Exception {
		
	    Date dateInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
	    ResponseJson json = new ResponseJson();
	    
	    if(score < 0 || score > 100) {
		    json.setCode("4001");
		    json.setError("Score value shoule be 0-100");
		    return json;
	    }
	    
	    // query by name and input time
		Score palyerScore = scoreService.findByPlayerandTime(name.toLowerCase(), dateInput);
		if(palyerScore != null) {
		    json.setCode("4002");
		    json.setError("Same player with the same input time was posted");
		    return json;
		}
		
		Score scoreObj = new Score();
	    scoreObj.setPlayer(name);
	    scoreObj.setScore(score);
	    
	
	    scoreObj.setTime(dateInput);
	    Score returnScore = scoreService.save(scoreObj);
		
	    json.setCode("0000");
	    json.setMessage(returnScore);
		return json;
	}

	@GetMapping("id/{id}")
	public ResponseJson getPlayerScore(@PathVariable long id) throws Exception {
		 
		 ResponseJson json = new ResponseJson();
		 Optional<Score> palyerScore = scoreService.findById(id);  //query by id
		 if(palyerScore.orElse(null) == null) {
			    json.setCode("4003");
			    json.setError(id + " id was not found from database");
		 } else {
			    json.setCode("0000");
			    json.setMessage(palyerScore.orElse(null));
		 }
		 
		 return json;
		
	}
	
	@DeleteMapping("id/{id}")
	public ResponseJson delPlayerScore(@PathVariable long id) throws Exception {
		 
		 ResponseJson json = new ResponseJson();
		 Optional<Score> palyerScore = scoreService.findById(id);  //query by id
		 if(palyerScore.orElse(null) == null) {
			    json.setCode("4003");
			    json.setError(id + " id was not found from database");
		 } else {
			    scoreService.deleteById(id);  //delete by id
			    json.setCode("0000");
			    json.setMessage(id + " id was removed from database");
		 }
		 
		 return json;
		
	}
}
