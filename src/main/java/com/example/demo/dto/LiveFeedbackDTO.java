package com.example.demo.dto;

import com.example.demo.enums.LiveEvaluation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LiveFeedbackDTO {
	private int id;
	
	private LiveEvaluation evaluation;
	private boolean recommended;
	private String suggestedTheme;
	private String opinion;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LiveEvaluation getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(LiveEvaluation evaluation) {
		this.evaluation = evaluation;
	}
	public boolean isRecommended() {
		return recommended;
	}
	public void setRecommended(boolean recommended) {
		this.recommended = recommended;
	}
	public String getSuggestedTheme() {
		return suggestedTheme;
	}
	public void setSuggestedTheme(String suggestedTheme) {
		this.suggestedTheme = suggestedTheme;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	@Override
	public String toString() {
		return "LiveFeedbackDTO [id=" + id + ", evaluation=" + evaluation + ", recommended=" + recommended
				+ ", suggestedTheme=" + suggestedTheme + ", opinion=" + opinion + "]";
	}
}
