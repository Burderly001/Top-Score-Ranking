package com.burderly.topranking.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.burderly.topranking.entity.Score;
import com.burderly.topranking.repository.ScoreRepository;



@Service
@ComponentScan
public class ScoreServiceImp implements ScoreService{
	
	@Autowired
	ScoreRepository scoreRepository;

//	@Override
//	public List<Score> findPlayer(String player) {
//		// TODO Auto-generated method stub
//		return scoreRepository.findPlayer(player);
//	}

//	@Override
//	public List<Score> findAll(String player) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public List<Score> findAll(String[] players) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public Optional<Score> find(long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Score save(Score score) {
		// TODO Auto-generated method stub
		return scoreRepository.save(score);
	}

	@Override
	public Score findByPlayerandTime(String player, Date inputtime) {
		// TODO Auto-generated method stub
		return scoreRepository.findByPlayerandTime(player, inputtime);
	}

	@Override
	public Optional<Score> findById(Long id) {
		// TODO Auto-generated method stub
		return scoreRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		scoreRepository.deleteById(id);
		
	}

	@Override
	public List<Score> findAllByPlayer(String player, Pageable paging) {
		// TODO Auto-generated method stub
		return scoreRepository.findAllByPlayerIgnoreCase(player, paging);
	}

	@Override
	public List<Score> findAllByPlayer(String player) {
		// TODO Auto-generated method stub
		return scoreRepository.findAllByPlayerIgnoreCase(player);
	}

	@Override
	public List<Score> findAllByPlayersandStart(List<String> players, Date start) {
		// TODO Auto-generated method stub
		return scoreRepository.findAllByPlayersandStart(players, start);
	}

	@Override
	public List<Score> findAllByStart(Date start) {
		// TODO Auto-generated method stub
		return scoreRepository.findAllByStart(start);
	}

	@Override
	public List<Score> findAllByEnd(Date end) {
		// TODO Auto-generated method stub
		return scoreRepository.findAllByEnd(end);
	}

	@Override
	public List<Score> findAllByStartEnd(Date start, Date end) {
		// TODO Auto-generated method stub
		return scoreRepository.findAllByStartEnd(start, end);
	}

	@Override
	public List<Score> findAllByPlayersandEnd(List<String> players, Date end) {
		// TODO Auto-generated method stub
		return scoreRepository.findAllByPlayersandEnd(players, end);
	}
	
	
	
    // paging

	@Override
	public List<Score> findAllByPlayersandStartPage(List<String> players, Date start, Pageable paging) {
		// TODO Auto-generated method stub
		return scoreRepository.findAllByPlayersandStartPage(players, start, paging);
	}

	@Override
	public List<Score> findAllByPlayersandEndPage(List<String> players, Date end, Pageable paging) {
		// TODO Auto-generated method stub
		return scoreRepository.findAllByPlayersandEndPage(players, end, paging);
	}

	@Override
	public List<Score> findAllByStartPage(Date start, Pageable paging) {
		// TODO Auto-generated method stub
		return scoreRepository.findAllByStartPage(start, paging);
	}

	@Override
	public List<Score> findAllByEndPage(Date end, Pageable paging) {
		// TODO Auto-generated method stub
		return scoreRepository.findAllByEndPage(end, paging);
	}

	@Override
	public List<Score> findAllByStartEndPage(Date start, Date end, Pageable paging) {
		// TODO Auto-generated method stub
		return scoreRepository.findAllByStartEndPage(start, end, paging);
	}

	
	//History score
	@Override
	public List<Score> findAllByPlayerTop(String player) {
		// TODO Auto-generated method stub
		return scoreRepository.findAllByPlayerTop(player);
	}

	@Override
	public List<Score> findAllByPlayerLow(String player) {
		// TODO Auto-generated method stub
		return scoreRepository.findAllByPlayerLow(player);
	}

	@Override
	public String findAllByPlayerAvg(String player) {
		// TODO Auto-generated method stub
		return scoreRepository.findAllByPlayerAvg(player);
	}
	
	
	
	

}
