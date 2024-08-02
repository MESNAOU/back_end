package com.example.demo.entities;

import com.example.demo.dto.LiveFeedbackDTO;
import com.example.demo.enums.LiveEvaluation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class LiveFeedback {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "generated")
	private int id;
	
	private LiveEvaluation evaluation;
	
	@Column(nullable = false)
	private boolean recommended;
	
	private String suggestedTheme;
	private String opinion;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="live_id")
	private Live live;
	
	
	public LiveFeedback(LiveFeedbackDTO dto, Live l) {
		this.id = dto.getId();
		this.evaluation = dto.getEvaluation();
		this.recommended = dto.isRecommended();
		this.suggestedTheme = dto.getSuggestedTheme();
		this.opinion = dto.getOpinion();
		this.setLive(l);
	}

	public LiveFeedback() {
		super();
	}

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

	public Live getLive() {
		return live;
	}

	public void setLive(Live live) {
		this.live = live;
	}
	
	
}
