package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.LiveFeedback;
import java.util.List;


@Repository
public interface LiveFeedbackRepository extends JpaRepository<LiveFeedback, Integer>{
	List<LiveFeedback> findByLiveId(int id);
}
