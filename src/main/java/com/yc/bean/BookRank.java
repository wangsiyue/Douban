package com.yc.bean;

import java.io.Serializable;

public class BookRank implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private Long rid;
	private Long bid;
	private Integer scores;
	private String ip;
	public Long getRid() {
		return rid;
	}
	public void setRid(Long rid) {
		this.rid = rid;
	}
	public Long getBid() {
		return bid;
	}
	public void setBid(Long bid) {
		this.bid = bid;
	}
	public Integer getScores() {
		return scores;
	}
	public void setScores(Integer scores) {
		this.scores = scores;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	

}
