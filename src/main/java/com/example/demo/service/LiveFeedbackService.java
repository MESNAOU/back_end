package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.LiveNotFoundException;
import com.example.demo.dto.LiveFeedbackDTO;
import com.example.demo.entities.Live;
import com.example.demo.entities.LiveFeedback;
import com.example.demo.enums.LiveEvaluation;
import com.example.demo.repositories.LiveFeedbackRepository;
import com.example.demo.repositories.LiveRepository;

@Service
public class LiveFeedbackService {
	@Autowired
	private LiveFeedbackRepository liveFeedbackRepo;
	@Autowired
	private LiveRepository liveRepo;

	public List<String> getOpinions(int liveId){
		List<LiveFeedback> feedbackList = this.liveFeedbackRepo.findByLiveId(liveId);
		return feedbackList.stream()
                .map(LiveFeedback::getOpinion)
                .collect(Collectors.toList());
	}
	
	public List<String> getSuggestedThemes(int liveId){
		List<LiveFeedback> feedbackList = this.liveFeedbackRepo.findByLiveId(liveId);
		return feedbackList.stream()
                .map(LiveFeedback::getSuggestedTheme)
                .collect(Collectors.toList());
	}
	
	public Map<LiveEvaluation, Integer> getEvaluation(int liveId){
		List<LiveFeedback> feedbackList = this.liveFeedbackRepo.findByLiveId(liveId);
		Map<LiveEvaluation, Integer> evaluationCount = new HashMap<>();
		
		for (LiveEvaluation evaluation : LiveEvaluation.values()) {
	        evaluationCount.put(evaluation, 0);
	    }
		
		feedbackList.forEach(feedback -> {
	        LiveEvaluation evaluation = feedback.getEvaluation();
	        evaluationCount.put(evaluation, evaluationCount.get(evaluation) + 1);
	    });

	    return evaluationCount;
	}
	
	public Map<Boolean, Integer> getRecommended(int liveId) {
	    List<LiveFeedback> feedbackList = this.liveFeedbackRepo.findByLiveId(liveId);
	    Map<Boolean, Integer> recommendedCount = new HashMap<>();

	    recommendedCount.put(true, 0);
	    recommendedCount.put(false, 0);

	    feedbackList.forEach(feedback -> {
	        boolean recommended = feedback.isRecommended();
	        recommendedCount.put(recommended, recommendedCount.get(recommended) + 1);
	    });

	    return recommendedCount;
	}
	
	public void createFeedback ( LiveFeedbackDTO feedbackDTO, int id) throws LiveNotFoundException {
		Live l = liveRepo.findById(id)
				.orElseThrow(()-> new LiveNotFoundException("Le live d'id "+id+" n'existe pas"));
		System.out.println("livefeedbackdto: "+feedbackDTO.toString());
		LiveFeedback feedback = new LiveFeedback(feedbackDTO, l);
		this.liveFeedbackRepo.save(feedback);
	}
}
