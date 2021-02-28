package com.burderly.topranking.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;


//Entity for score

@Entity
@Table(name = "score")
public class Score {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "player", nullable = false, columnDefinition="varchar(50)")
	private String player;
	
	@Column(name = "score", nullable = false, columnDefinition="int(3)")
	private int score;
	
	
	@CreationTimestamp
	@Column(name = "create_time", nullable = false, updatable = false)
	private Date createtime;
	
	@Column(name = "user_input_time", nullable = false, columnDefinition="datetime")
	private Date time;

	
	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;     //.toLowerCase();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
