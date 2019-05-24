package com.chainsys.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Quiz {
	
	private int id;
	private String name;
	private Topics topics;
	private LocalTime durationTime;
	private LocalDate expiredDate;
	private int status;
	private int attendedCount;
	private int noOfQuestion;
	public int getNoOfQuestion() {
		return noOfQuestion;
	}
	public void setNoOfQuestion(int noOfQuestion) {
		this.noOfQuestion = noOfQuestion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Topics getTopics() {
		return topics;
	}
	public void setTopics(Topics topics) {
		this.topics = topics;
	}
	public LocalTime getDurationTime() {
		return durationTime;
	}
	public void setDurationTime(LocalTime durationTime) {
		this.durationTime = durationTime;
	}
	public LocalDate getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(LocalDate expiredDate) {
		this.expiredDate = expiredDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAttendedCount() {
		return attendedCount;
	}
	public void setAttendedCount(int attendedCount) {
		this.attendedCount = attendedCount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + attendedCount;
		result = prime * result + ((durationTime == null) ? 0 : durationTime.hashCode());
		result = prime * result + ((expiredDate == null) ? 0 : expiredDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + status;
		result = prime * result + ((topics == null) ? 0 : topics.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quiz other = (Quiz) obj;
		if (attendedCount != other.attendedCount)
			return false;
		if (durationTime == null) {
			if (other.durationTime != null)
				return false;
		} else if (!durationTime.equals(other.durationTime))
			return false;
		if (expiredDate == null) {
			if (other.expiredDate != null)
				return false;
		} else if (!expiredDate.equals(other.expiredDate))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (status != other.status)
			return false;
		if (topics == null) {
			if (other.topics != null)
				return false;
		} else if (!topics.equals(other.topics))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Quiz [id=" + id + ", name=" + name + ", topics=" + topics + ", durationTime=" + durationTime
				+ ", expiredDate=" + expiredDate + ", status=" + status + ", attendedCount=" + attendedCount + "]";
	}
	

}
