package com.burderly.topranking.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.burderly.topranking.entity.ResponseJson;
import com.burderly.topranking.entity.Score;
import com.burderly.topranking.services.ScoreService;

@RestController
@RequestMapping("api/scores")
public class ScoresController {
	@Autowired
	ScoreService scoreService;
	
	@GetMapping("player/{name}")
	public ResponseJson getPlayerScores(@PathVariable String name,
			@RequestParam(name = "page", required = false) String page, 
			@RequestParam(name = "pagesize", required = false) String pagesize) throws Exception {
		
		ResponseJson json = new ResponseJson();
		List <Score>  players = null;
		
		
		if(page != null && pagesize != null) {
			int pageInt = (Integer.valueOf(page) < 1) ? 0:(Integer.valueOf(page) - 1);
			int pageSizeInt = (Integer.valueOf(pagesize) < 1) ? 1 : Integer.valueOf(pagesize);
			Pageable paging = PageRequest.of(pageInt, pageSizeInt); //Paging
			players = scoreService.findAllByPlayer(name, paging);
		} else {
			players = scoreService.findAllByPlayer(name);  // not Paging
		}

		
	    json.setCode("0000");
	    json.setMessage(players);
		
		return json;
	}

	@GetMapping("/players")
	public ResponseJson getAllPlayerScoresByDate(@RequestParam(name = "startdate", required = false) String startdate,
			@RequestParam(name = "enddate", required = false) String enddate,
			@RequestParam(name = "players", required = false) List <String> players,
			@RequestParam(name = "page", required = false) String page, 
			@RequestParam(name = "pagesize", required = false) String pagesize) throws Exception {
		
		ResponseJson json = new ResponseJson();
		List <String> playersList = null;
		if(players != null && players.size()>=1) { // has players
			playersList = players;
		    ListIterator<String> iterator = playersList.listIterator();
		    while (iterator.hasNext())
		    {
		        iterator.set(iterator.next().toLowerCase());
		    }
		}
		
		Pageable paging = null;
		if(page != null && pagesize != null) {
			int pageInt = (Integer.valueOf(page) < 1) ? 0:(Integer.valueOf(page) - 1);
			int pageSizeInt = (Integer.valueOf(pagesize) < 1) ? 1 : Integer.valueOf(pagesize);
			paging = PageRequest.of(pageInt, pageSizeInt); //Paging
		}
		
		Date start = null;
		Date end = null;
		
		if(startdate != null) {
			start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startdate);
		}
		
		if(enddate != null) {
			end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(enddate);
		}
		
		List <Score> playersJson = null;
		if(playersList != null) {
			if(start == null && end == null) {
			    json.setCode("4004");
			    json.setError("Query missing startdate or enddate");
			    return json;
			} else {
				if(start != null) {
					if(paging != null) {
						playersJson = scoreService.findAllByPlayersandStartPage(playersList, start, paging);
					} else {
						playersJson = scoreService.findAllByPlayersandStart(playersList, start);
					}
					
				} else {
					if(end != null)  {
						if(paging != null) {
							playersJson = scoreService.findAllByPlayersandEndPage(playersList, end, paging);
						} else {
							playersJson = scoreService.findAllByPlayersandEnd(playersList, end);
						}
						
					}
				}	
			}
			
		} else {
			if(start == null && end == null) {
			    json.setCode("4005");
			    json.setError("startdate or enddate does not provide");
			    return json;
			}
			if(start != null && end != null) {
				if(paging != null) {
					playersJson = scoreService.findAllByStartEndPage(start, end, paging);
				} else {
					playersJson = scoreService.findAllByStartEnd(start, end);
				}
				
			} else {
				if(start != null) {
					if(paging != null) {
						playersJson = scoreService.findAllByStartPage(start, paging);
					} else {
						playersJson = scoreService.findAllByStart(start);
					}
					
				} else {
					if(end != null) {
						if(paging != null) {
							playersJson = scoreService.findAllByEndPage(end, paging);
						} else {
							playersJson = scoreService.findAllByEnd(end);
						}
						
					}
				}				

			}
			
		}
		
	    json.setCode("0000");
	    json.setMessage(playersJson);
		
		return json;
	}
}
