package com.burderly.topranking.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.burderly.topranking.entity.Score;



@Repository
public interface ScoreRepository extends JpaRepository<Score, Long>{
	// userRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
	// Page<User> findAllUsersWithPagination(Pageable pageable);
	
//	@Query(
//			  value = "SELECT * FROM Users ORDER BY id", 
//			  countQuery = "SELECT count(*) FROM Users", 
//			  nativeQuery = true)
//			Page<User> findAllUsersWithPagination(Pageable pageable);
	
//	@Query(value = "SELECT * FROM Users u WHERE u.status = :status and u.name = :name", 
//			  nativeQuery = true)
//			User findUserByStatusAndNameNamedParamsNative(
//			  @Param("status") Integer status, @Param("name") String name);
	
	
//	public List<Score> findPlayer(String player);
	
	//public List<Score> findAll(String player);
	
//	@Query(value="select * from score where player in ?1" , nativeQuery=true)
//	public List<Score> findAll(String players[]);
	
//	public Optional <Score> find(Long id);
	
	@Override
	//public Score save(Score score);
	public <S extends Score> S save(S score);
	
	@Query(value="select * from score where LOWER(player)=?1 and user_input_time=?2", nativeQuery=true)
	public Score findByPlayerandTime(String player, Date inputtime);
	
	public Optional<Score> findById(Long id);
	
	//@Query(value="delete * from score where id=?1", nativeQuery=true)
	public void deleteById(Long id);
	
	public List <Score> findAllByPlayerIgnoreCase(String player, Pageable paging);
	public List <Score> findAllByPlayerIgnoreCase(String player);
	
	
	
	// not paging
	@Query(value="select * from score where LOWER(player) in ?1 and user_input_time >?2", nativeQuery=true)
	public List <Score> findAllByPlayersandStart(List <String> players, Date start);
	
	@Query(value="select * from score where LOWER(player) in ?1 and user_input_time <?2", nativeQuery=true)
	public List <Score> findAllByPlayersandEnd(List <String> players, Date end);
	
	@Query(value="select * from score where user_input_time >?1", nativeQuery=true)
	public List <Score> findAllByStart(Date start);
	
	@Query(value="select * from score where user_input_time <?1", nativeQuery=true)
	public List <Score> findAllByEnd(Date end);
	
	@Query(value="select * from score where user_input_time >?1 and user_input_time <?2", nativeQuery=true)
	public List <Score> findAllByStartEnd(Date start, Date end); 
	
	
	//paging
	@Query(value="select * from score where LOWER(player) in ?1 and user_input_time >?2", nativeQuery=true)
	public List <Score> findAllByPlayersandStartPage(List <String> players, Date start, Pageable paging);
	
	@Query(value="select * from score where LOWER(player) in ?1 and user_input_time <?2", nativeQuery=true)
	public List <Score> findAllByPlayersandEndPage(List <String> players, Date end, Pageable paging);
	
	@Query(value="select * from score where user_input_time >?1", nativeQuery=true)
	public List <Score> findAllByStartPage(Date start, Pageable paging);
	
	@Query(value="select * from score where user_input_time <?1", nativeQuery=true)
	public List <Score> findAllByEndPage(Date end, Pageable paging);
	
	@Query(value="select * from score where user_input_time >?1 and user_input_time <?2", nativeQuery=true)
	public List <Score> findAllByStartEndPage(Date start, Date end, Pageable paging); 
	
	
	//history score
	@Query(value="select * from score s where LOWER(player)=?1 and s.score=(select MAX(score) from score LIMIT 1)", nativeQuery=true)
	public List <Score> findAllByPlayerTop(String player);
	
	
	@Query(value="select * from score s where LOWER(player)=?1 and s.score=(select MIN(score) from score LIMIT 1)", nativeQuery=true)
	public List <Score> findAllByPlayerLow(String player);
	
	
	//@Query(value="select AVG(s.score) from score s where s.player=?1", nativeQuery=true)
	@Query(value = "SELECT avg(e.score) as score FROM score e WHERE e.player = ?1" , nativeQuery = true)
	public String findAllByPlayerAvg(String player);
	

}
