package com.example.demo.service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.AdministrateurNotFoundException;
import com.example.demo.Exception.LiveNotFoundException;
import com.example.demo.Exception.ResponsableNotFoundException;
import com.example.demo.IES.Mailer.MailSending;
import com.example.demo.dto.LiveDTO;
import com.example.demo.dto.LiveForCreationDTO;
import com.example.demo.entities.Administrateur;
import com.example.demo.entities.Live;
import com.example.demo.entities.Responsable;
import com.example.demo.entities.Theme;
import com.example.demo.mappers.LiveMapper;
import com.example.demo.mappers.QuestionMapper;
import com.example.demo.mappers.ResponsableMapper;
import com.example.demo.mappers.ThemeMapper;
import com.example.demo.repositories.AdministrateurRepository;
import com.example.demo.repositories.JeuneRepository;
import com.example.demo.repositories.LiveRepository;
import com.example.demo.repositories.MedecinRepository;
import com.example.demo.repositories.ProfessionnelRepository;
import com.example.demo.repositories.ThemeRepository;

import jakarta.transaction.Transactional;

@Service
public class LiveService {
    @Autowired
    LiveRepository liveRepository;
    @Autowired
    MailSending sender;
    @Autowired
    ThemeRepository thematiqueRepository;
    @Autowired
    ThemeMapper mapperTheme;
    @Autowired
    ResponsableMapper medecinMapper;
    @Autowired
    QuestionMapper mapperQuestions;
    @Autowired
    LiveMapper mapperLive;
    @Autowired
    JeuneService jeuneService;
    @Autowired
    JeuneRepository JR;
    @Autowired
    MedecinRepository medecinRepository;
    @Autowired
    ProfessionnelRepository professionelRepository;
    @Autowired
    AdministrateurRepository administrateurRepository;



    public List<LiveDTO> getAllLives(){
        List<Live> L=this.liveRepository.findAll();
        return this.mapperLive.allLivesToDtoLives(L);
    }
    
    // live passed
    public List<LiveDTO> getPassedLives(){
        LocalDateTime Now=LocalDateTime.now();
        List<LiveDTO> LD=new ArrayList<>();
        List<Live> L=this.liveRepository.findAll();
        for(int i=0;i<L.size();i++){
            LiveDTO DTO=this.mapperLive.liveToDTOLive(L.get(i));
            if(DTO.getDate().isBefore(Now)){
                LD.add(DTO);
            }
        }
        return  LD;
    }
    public List<LiveDTO> getAllByAdmin(int id){
        List<Live> L=this.liveRepository.findByAdminId(id);
        return this.mapperLive.allLivesToDtoLives(L);
    }
    // live on going
    public List<LiveDTO> getongoing(int id){
        LocalDateTime Now=LocalDateTime.now();
        List<LiveDTO> LD=new ArrayList<>();
        List<Live> L=this.liveRepository.findByAdminId(id);
        for(int i=0;i<L.size();i++){
            LiveDTO DTO=this.mapperLive.liveToDTOLive(L.get(i));
            if(DTO.getDate().isAfter(Now)){
                LD.add(DTO);
            }
        }
        return  LD;
    }
    public List<LiveDTO> getongoingforuser(){
        LocalDateTime Now=LocalDateTime.now();
        List<LiveDTO> LD=new ArrayList<>();
        List<Live> L=this.liveRepository.findAll();
        for(int i=0;i<L.size();i++){
            LiveDTO DTO=this.mapperLive.liveToDTOLive(L.get(i));
            if(DTO.getDate().isAfter(Now)){
                LD.add(DTO);
            }
        }
        return  LD;
    }

    // to active / desactive a live
    public void activatdes(int id){
        Live l=this.liveRepository.findById(id).get();
        if(l.isActive()){
            l.setActive(false);
        }
        else{
            System.out.println(("you will receive the informations"));
            l.setActive(true);
                this.sender.setReceiver(l.getResponsable().getInfoUser().getMail());
                this.sender.setSub("test");
                String body="GET YOUR JOB THEMATIC"+l.getThematiques().getContenu()+"FOR SUBJECT"+l.getSubject()+"for the link"+l.getLienYoutube();
                this.sender.setSub("test");
                this.sender.setBody(body);
                Thread Thread=new Thread(this.sender);
                Thread.start();

        }
        this.liveRepository.save(l);
    }
    // animated by doctor or infrimrie
    public List<LiveDTO> getAllAnimated(Long id){
        List<Live> L=this.liveRepository.findByResponsableId(id);
        List<LiveDTO> L1=this.mapperLive.allLivesToDtoLives(L);
        List<LiveDTO> L2=new ArrayList<>();
        LocalDateTime D=LocalDateTime.now();
        for(int i=0;i<L1.size();i++){
            if(L1.get(i).getActive()){
                if(L1.get(i).getDate().isBefore(D)){
                    L2.add(L1.get(i));
                }
            }
        }
        return L2;
    }

    // on going to animate by doctor
    public  List<LiveDTO> getongoingToan(Long id){
        List<Live> L=this.liveRepository.findByResponsableId(id);
        List<LiveDTO> L1=this.mapperLive.allLivesToDtoLives(L);
        //System.out.println(L1);
        List<LiveDTO> L2=new ArrayList<>();
        LocalDateTime D=LocalDateTime.now();
        for(int i=0;i<L1.size();i++){
            if(L1.get(i).getActive()){
                if(L1.get(i).getDate().isAfter(D)){
                    L2.add(L1.get(i));
                }
            }
        }
        return L2;
    }

    // live not activet yet
    public List<LiveDTO> getfileattentetoactive(int id){
        List<LiveDTO> L1=this.getongoing(id);
        List<LiveDTO> L2=new ArrayList<>();
        for(int i=0;i<L1.size();i++){
            if(L1.get(i).getActive()){

            }
            else{
                L2.add(L1.get(i));
            }
        }
        return L2;
    }
    //live activated
    public List<LiveDTO> getfileattente(int id){
        List<LiveDTO> L1=this.getongoing(id);
        List<LiveDTO> L2=new ArrayList<>();
        for(int i=0;i<L1.size();i++){
            if(L1.get(i).getActive()){
                L2.add(L1.get(i));

            }

        }
        return L2;
    }
    public List<LiveDTO> getAllfileattenteforuser(){
        List<LiveDTO> L1=this.getongoingforuser();
        List<LiveDTO> L2=new ArrayList<>();
        for(int i=0;i<L1.size();i++){
            if(L1.get(i).getActive()){
                L2.add(L1.get(i));

            }

        }
        return L2;
    }

    public List<LiveDTO> getonquestions(int id){
        List<LiveDTO> L1=this.getfileattente(id);
        List<LiveDTO> Sending=new ArrayList<>();
        LocalDateTime D=LocalDateTime.now();

        for(int i=0;i<L1.size();i++){
            long daysBetween = ChronoUnit.DAYS.between(D, L1.get(i).getDate());

            if(daysBetween>3&&daysBetween<25){
                Sending.add(L1.get(i));
            }
        }
        return Sending;
    }
    public List<LiveDTO> getonquestionsforuser(){
        List<LiveDTO> L1=this.getAllfileattenteforuser();
        List<LiveDTO> Sending=new ArrayList<>();
        LocalDate D=LocalDate.now();

        for(int i=0;i<L1.size();i++){
            Period P=Period.between(D,L1.get(i).getDate().toLocalDate());
            int days=P.getDays();
            long daysBetween = ChronoUnit.DAYS.between(D, L1.get(i).getDate().toLocalDate());

            System.out.println(daysBetween);
            if(daysBetween>3&&daysBetween<25){
                Sending.add(L1.get(i));
            }
        }
        return Sending;
    }

    //phase j-3
    @Transactional
    //@Scheduled(cron = "0 0 0 * * *")
    public List<LiveDTO> getonfinal(int id){
        List<LiveDTO> L1=this.getfileattente(id);
        List<LiveDTO> L2=new ArrayList<>();
        LocalDateTime D=LocalDateTime.now();
        for(int i=0;i<L1.size();i++){

            long daysBetween = ChronoUnit.DAYS.between(D, L1.get(i).getDate());

            if(daysBetween==3){
                System.out.println("a mesg will be sended to you");
                L2.add(L1.get(i));
            }
            if(daysBetween<3){
                L2.add(L1.get(i));
            }
        }

        return L2;
    }
    @Transactional
    //@Scheduled(cron = "0 0 0 * * *")
    public List<LiveDTO> getonfinalforuser(){
        List<LiveDTO> L1=this.getAllfileattenteforuser();
        List<LiveDTO> L2=new ArrayList<>();
        LocalDateTime D=LocalDateTime.now();
        for(int i=0;i<L1.size();i++){
            long daysBetween = ChronoUnit.DAYS.between(D, L1.get(i).getDate());

            if(daysBetween<=3){
                L2.add(L1.get(i));
            }
        }

        return L2;
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void checkisActivated(){
        LocalDateTime D=LocalDateTime.now();
        List<LiveDTO> L=this.getongoingforuser();
        for(int i=0;i<L.size();i++){
            long daysBetween = ChronoUnit.DAYS.between(D, L.get(i).getDate());

            if(L.get(i).getActive()==false) {
                if (daysBetween == 45) {
                    L.get(i).setActive(true);
                    this.sender.setReceiver("hamza.elmalki1234@gmail.com");
                    this.sender.setSub("test");
                    String body="GET YOUR JOB THEMATIC"+L.get(i).getThematique().getContenu()+"FOR SUBJECT"+L.get(i).getSubject()+"for the link"+L.get(i).getLienYoutube();
                    this.sender.setBody(body);
                    Thread Thread=new Thread(this.sender);
                    Thread.start();
                }
            }
        }
    }

    public LiveDTO getSingleLive(int id){
        Live l=this.liveRepository.findById(id).get();
        System.out.println(l.getResponsable().getInfoUser().getMail());
        return this.mapperLive.liveToDTOLive(l);
    }
    
    @Transactional
    public void createLive(LiveForCreationDTO liveRequest, Long adminId) throws ResponsableNotFoundException, AdministrateurNotFoundException{
    	Responsable responsable;
    	String role = liveRequest.getResponsable().getRole();
    	Long responsableId = liveRequest.getResponsable().getId();
    	if (role.equals("Professionnel de Santé")) {
            responsable = professionelRepository.findById(responsableId)
                    .orElseThrow(() -> new ResponsableNotFoundException("Le professionnel de santé est introuvable"));
        } else {
            responsable = medecinRepository.findById(responsableId)
                    .orElseThrow(() -> new ResponsableNotFoundException("Le médecin est introuvable"));
        }
    	Administrateur admin = administrateurRepository.findById(adminId)
                .orElseThrow(() -> new AdministrateurNotFoundException("L'administrateur est introuvable"));
    	Live live = this.mapperLive.dtoLiveForCreationToLive(liveRequest, responsable, admin);

    	LocalDateTime D=LocalDateTime.now();
        long daysBetween = ChronoUnit.DAYS.between(D, live.getDate());
        Optional<Theme> P=this.thematiqueRepository.findByContenu(live.getThematiques().getContenu());
        if(P.isPresent()){
            live.setThematiques(P.get());
        }
        else{
            this.thematiqueRepository.save(live.getThematiques());
            Theme P2=this.thematiqueRepository.findByContenu(live.getThematiques().getContenu()).get();
            live.setThematiques(P2);
        }

        if(daysBetween<45){

        	live.setActive(true);
            this.sender.setReceiver(live.getResponsable().getInfoUser().getMail());
            this.sender.setSub("LIVE AFFECTATION TITLED : "+liveRequest.getSubject());
            String body="GET YOUR JOB THEMATIC "+live.getThematiques().getContenu()+" FOR SUBJECT "+liveRequest.getSubject()+" for the link "+liveRequest.getLienYoutube();
            this.sender.setBody(body);
            Thread Thread=new Thread(this.sender);
            Thread.start();
        }
        this.liveRepository.save(live);
    }
    public void updateLive(LiveDTO L,Long adminId,int liveId) throws ResponsableNotFoundException, LiveNotFoundException{
    	Live existingLive = liveRepository.findById(liveId)
    			.orElseThrow(() -> new LiveNotFoundException("Le live est introuvable"));
    	Long responsableId = L.getResponsable().getId();
    	if (responsableId != existingLive.getResponsable().getId()) {
    		Responsable responsable;
        	String role = L.getResponsable().getRole();
        	if (role.equals("Professionnel de Santé")) {
                responsable = professionelRepository.findById(responsableId)
                        .orElseThrow(() -> new ResponsableNotFoundException("Le professionnel de santé est introuvable"));
            } else {
                responsable = medecinRepository.findById(responsableId)
                        .orElseThrow(() -> new ResponsableNotFoundException("Le médecin est introuvable"));
            }
        	existingLive.setResponsable(responsable);
    	}
    	existingLive.setLienStreamYard(L.getLienStreamYard());
    	existingLive.setLienYoutube(L.getLienYoutube());

        this.liveRepository.save(existingLive);
    }
    public void deleteLive(int id) throws LiveNotFoundException{
    	Live l = this.liveRepository.findById(id)
    			.orElseThrow(() -> new LiveNotFoundException("Le live d'id "+id+" est introuvable."));
        this.liveRepository.deleteById(id);
    }
}
