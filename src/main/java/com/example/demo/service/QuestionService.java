package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.QuestionDTO;
import com.example.demo.entities.Question;
import com.example.demo.mappers.QuestionMapper;
import com.example.demo.repositories.QuestionRepository;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuestionMapper mapper;
    @Autowired
    private OpenAiService openAiService;
    @Autowired
    private LiveService liveService;
    
    public List<QuestionDTO> getAllQuestions(int id){
    	return this.mapper.getallDtoQuestions(this.questionRepository.findByLiveId(id));
    	/*
    	// Obtenir toutes les questions sous forme de QuestionDTO
        List<QuestionDTO> questions = this.mapper.getAllDtoQuestions(this.questionRepository.findByLiveId(id));
        
        // Extraire le contenu des questions
        List<String> questionsContenuList = questions.stream()
                .map(QuestionDTO::getContenu)
                .collect(Collectors.toList());
        
        String theme = liveService.getSingleLive.getContenu();
        
        // Traiter les questions (ajoutez votre logique de traitement ici, par exemple avec OpenAI API)
        List<String> processedList = this.processQuestions(questionsContenuList, theme);

        // Utiliser un compteur atomique pour incrémenter l'ID par item
        AtomicInteger counter = new AtomicInteger(1);

        // Convertir la liste traitée en une liste de QuestionDTO avec des IDs incrémentés
        List<QuestionDTO> processedQuestionDTOList = processedList.stream()
                .map(item -> new QuestionDTO(counter.getAndIncrement(), item))
                .collect(Collectors.toList());

        return processedQuestionDTOList;
        */
    }
    
    public String processQuestions(List<String> questions) {
        // Construire le prompt
        StringBuilder promptBuilder = new StringBuilder()
                .append("Je veux que la réponse soit exclusivement un objet JSON de cette forme :\n")
                .append("{\n")
                .append("\"questions résumées\": [\n")
                .append("//question1,\n")
                .append("//question2,\n")
                .append("//...etc\n")
                .append("]\n")
                .append("}\n")
                .append("Lesquelles de ces questions sont dans ce sujet \"Tabac").append("\" :\n");

        // Ajouter les questions au prompt
        for (int i = 0; i < questions.size(); i++) {
            promptBuilder.append(i + 1).append(") ").append(questions.get(i)).append("\n");
        }
        
        promptBuilder.append("après cela essaie de regrouper les questions que vous avez jugés lié au sujet en moins de questions résumées en français.");
        
        // Configurer la demande de complétion
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(promptBuilder.toString())
                .maxTokens(150) // Ajustez selon la longueur attendue de la réponse
                .temperature(0.7) // Ajustez la créativité de la réponse
                .build();

        // Obtenir la réponse de l'API OpenAI
        CompletionResult result = null;
        try {
        	result = openAiService.createCompletion("text-davinci-003", completionRequest);
        } catch (Exception e) {
        	e.getStackTrace();
        	System.out.println("chatgpt error");
        }
        

        System.out.println("result : "+result);
        
        // Retourner la réponse
        return result.getChoices().get(0).getText();
    }

    public void createOne(Question Q){
        this.questionRepository.save(Q);
    }


}
