package com.micode.webapp.wechat.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckListAnswer {
	
	 private String answer;
	 private String next;
	public CheckListAnswer() {}
	public CheckListAnswer( 
			@JsonProperty("answer") String answer, 
			@JsonProperty("next")String next) {
		this.answer = answer;
		this.next = next;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
}
