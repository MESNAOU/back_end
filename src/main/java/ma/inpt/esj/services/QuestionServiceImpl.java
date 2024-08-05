package ma.inpt.esj.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;

import ma.inpt.esj.dto.LiveDTO;
import ma.inpt.esj.dto.QuestionDTO;
import ma.inpt.esj.entities.Question;
import ma.inpt.esj.mappers.QuestionMapper;
import ma.inpt.esj.repositories.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {
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

	@Override
	public List<Question> getQuestionsByLiveIdAndJeuneId(int liveId, int jeuneId) {
		return this.questionRepository.findByLiveIdAndJeuneId(liveId, jeuneId);
	}
	
	@Override
	public List<LiveDTO> getonquestionsforuserId(int jeuneId, int limit) {
		List<LiveDTO> L1=liveService.getAllfileattenteforuser();
        List<LiveDTO> Sending=new ArrayList<>();
        LocalDate D=LocalDate.now();

        for(int i=0;i<L1.size();i++){
        	List<Question> questionList = this.getQuestionsByLiveIdAndJeuneId(L1.get(i).getId(), jeuneId);
        	if (questionList.size() >= limit)
        		continue;
            Period P=Period.between(D,L1.get(i).getDate().toLocalDate());
            System.out.println("id live question phase: "+L1.get(i).getId());
            System.out.println("size: "+questionList.size());
            int days=P.getDays();
            long daysBetween = ChronoUnit.DAYS.between(D, L1.get(i).getDate().toLocalDate());

            System.out.println(daysBetween);
            if(daysBetween>3&&daysBetween<=28){
                Sending.add(L1.get(i));
            }
        }
        return Sending;
	}
	
	@Override
	public List<LiveDTO> getonfinalforuserId(int jeuneId, int limit) {
		List<LiveDTO> L1=liveService.getAllfileattenteforuser();
        List<LiveDTO> L2=new ArrayList<>();
        LocalDateTime D=LocalDateTime.now();
        for(int i=0;i<L1.size();i++){
            long daysBetween = ChronoUnit.DAYS.between(D, L1.get(i).getDate());

            if(daysBetween<=3){
                L2.add(L1.get(i));
            } else if (daysBetween>3&&daysBetween<=28) {
            	List<Question> questionList = this.getQuestionsByLiveIdAndJeuneId(L1.get(i).getId(), jeuneId);
            	if (questionList.size() > limit)
            		L2.add(L1.get(i));
            }
        }
        return L2;
	}
}
