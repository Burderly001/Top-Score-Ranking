package com.burderly.topranking.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.burderly.topranking.entity.Score;



@Service
public interface ScoreService {
	
	public Score save(Score score);

//	public List<Score> findPlayer(String player);
	
//	public List<Score> findAll(String player);
	
//	public List<Score> findAll(String players[]);
	
//	public Optional <Score> find(long id);
	
	public Score findByPlayerandTime(String player, Date inputtime);
	public Optional<Score> findById(Long id);
	public void deleteById(Long id);
	
	public List <Score> findAllByPlayer(String player, Pageable paging);
	public List <Score> findAllByPlayer(String player);
	
	// Players with start and end date
	public List <Score> findAllByPlayersandStart(List <String> players, Date start);
	
	public List <Score> findAllByPlayersandEnd(List <String> players, Date end);
	
	public List <Score> findAllByStart(Date start);
	
	public List <Score> findAllByEnd(Date end);
	
	public List <Score> findAllByStartEnd(Date start, Date end); 
	
	
	//paging
	public List <Score> findAllByPlayersandStartPage(List <String> players, Date start, Pageable paging);
	
	public List <Score> findAllByPlayersandEndPage(List <String> players, Date end, Pageable paging);
	
	public List <Score> findAllByStartPage(Date start, Pageable paging);
	
	public List <Score> findAllByEndPage(Date end, Pageable paging);
	
	public List <Score> findAllByStartEndPage(Date start, Date end, Pageable paging); 
	
	
	//History score
	
	public List <Score> findAllByPlayerTop(String player);
	
	public List <Score> findAllByPlayerLow(String player);
	
	public String findAllByPlayerAvg(String player);
	
}
