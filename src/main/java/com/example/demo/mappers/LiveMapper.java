package com.example.demo.mappers;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LiveDTO;
import com.example.demo.dto.LiveForCreationDTO;
import com.example.demo.dto.ThemeDTO;
import com.example.demo.entities.Administrateur;
import com.example.demo.entities.Live;
import com.example.demo.entities.Responsable;


@Service
public class LiveMapper {
    @Autowired
    QuestionMapper mapperQuestions;
    @Autowired
    ThemeMapper mapperTheme;
    @Autowired
    ResponsableMapper responsableMapper;
    @Autowired
    AdministrateurMapper administrateurMapper;
    
    public List<LiveDTO> allLivesToDtoLives(List<Live> L){
        List<LiveDTO> LD=new ArrayList<>();
        for(int i=0;i<L.size();i++){
            LiveDTO DTO= new LiveDTO(L.get(i).getId(),L.get(i).getSubject(),L.get(i).getDate(),L.get(i).getLienStreamYard(),L.get(i).getLienYoutube());
            //DTOADMIN D=new DTOADMIN(L.get(i).getAdmin().getId(),L.get(i).getAdmin().getInfoUser().getNom(), L.get(i).getAdmin().getInfoUser().getPrenom(),L.get(i).getAdmin().getInfoUser().getNumTel(),L.get(i).getAdmin().getInfoUser().getMail());

            DTO.setAdmin(administrateurMapper.adminToAdminDTO(L.get(i).getAdmin()));;
            DTO.setActive(L.get(i).isActive());

           // DTORESPONSABLE RES=this.Mapper.fromMedcine(L.get(i).getResponsable());

           /* ConcreteUtilisateur U=new ConcreteUtilisateur();
            U.setId(L.get(i).getCreateur().getId());
            U.setInfoUser(L.get(i).getCreateur().getInfoUser());*/
            
            DTO.setResponsable(this.responsableMapper.fromResponsable(L.get(i).getResponsable()));
            ThemeDTO LT=this.mapperTheme.getSingle(L.get(i).getThematiques());
            DTO.setThematique(LT);

            DTO.setQuestions(this.mapperQuestions.getallDtoQuestions(L.get(i).getQuestionsList()));

            LD.add(DTO);
        }
        return LD;
    }

    public   LiveDTO liveToDTOLive(Live l){

        LiveDTO LIVE=new LiveDTO(l.getId(),l.getSubject(),l.getDate(),l.getLienStreamYard(),l.getLienYoutube());
        /*
        LIVER.setId(l.getId());
        LIVER.setDate(l.getDate());
        LIVER.setSubject(l.getSubject());
        LIVER.setLienStreamYard(l.getLienStreamYard());
        LIVER.setLienYoutube(l.getLienYoutube());
        */
        //DTORESPONSABLE RES=this.Mapper.fromMedcine(l.getResponsable());
        LIVE.setAdmin(administrateurMapper.adminToAdminDTO(l.getAdmin()));
        LIVE.setActive(l.isActive());
       /* ConcreteUtilisateur U=new ConcreteUtilisateur();
        U.setId(l.getCreateur().getId());
        U.setInfoUser(l.getCreateur().getInfoUser());
        LIVER.setResponsable(U);*/
        LIVE.setResponsable(this.responsableMapper.fromResponsable(l.getResponsable()));
        //LIVER.setResponsable(RES);
        // LIVER.setResponsable(l.getResponsable());
        ThemeDTO LT=this.mapperTheme.getSingle(l.getThematiques());
        LIVE.setThematique(LT);

        LIVE.setQuestions(this.mapperQuestions.getallDtoQuestions(l.getQuestionsList()));

        return LIVE;
    }
    /*public List<Questions> Inverse(List<DTOQUESTIONS> P){
        List<Questions> LD=new ArrayList<>();
        for(int i=0;i<P.size();i++){
            Questions TE=new Questions();
            TE.setId(P.get(i).getId());
            TE.setContenu(P.get(i).getContenu());
            LD.add(TE);
        }
        return LD;
    }*/
    public  Live  dtoLiveToLive(LiveDTO liveRequest, Responsable responsable){
        Live live = new Live();
        //Responsable R=new Responsable();
        //ProfessionnelSante P=new ProfessionnelSante();
        live.setId(liveRequest.getId());
        live.setSubject(liveRequest.getSubject());
        live.setDate(liveRequest.getDate());
        live.setLienStreamYard(liveRequest.getLienStreamYard());
        live.setLienYoutube(liveRequest.getLienYoutube());
        live.setActive(liveRequest.getActive());
        live.setThematiques(this.mapperTheme.Iversing(liveRequest.getThematique()));
        live.setQuestionsList(this.mapperQuestions.Inverse(liveRequest.getQuestions()));
        // Créez l'entité Responsable associée
        
        /*
        Utilisateur responsable = new ConcreteUtilisateur();
        responsable.setId(liveRequest.getResponsable().getId());
        */
        //responsable.setId(liveRequest.getResponsable().getId());
       // responsable.setCin(liveRequest.getResponsable().getCin());
        //responsable.setSpecialite(liveRequest.getResponsable().getSpecialite());
        //responsable.setInpe(liveRequest.getResponsable().getInpe());
        //responsable.setPpr(liveRequest.getResponsable().getPpr());
        //responsable.setEstGeneraliste(liveRequest.getResponsable().getEstGeneraliste());
        //responsable.setEstMedcinESJ(liveRequest.getResponsable().getEstMedcinESJ());
        //live.setAdmin(new Administrateur(id,"","",""));



        // Associez le responsable à la live
        live.setResponsable(responsable);
        //List<PropositionThématique> P=this.M.Inverse(liveRequest.getThématiques());
        //T.setJeunes(P.getJeunes());
        return live;
    }
    /*
    public  Live Updating(DTOLIVE L){
        Live live=new Live();
        live.setSubject(L.getSubject());
        live.setDate(L.getDate());
        live.setLienYoutube(L.getLienYoutube());
        live.setLienStreamYard(L.getLienStreamYard());
        live.setThematiques(this.M.Iversing(L.getThématiques()));
        live.setActive(L.getActive());
        Responsable responsable = new Responsable(L.getResponsable());
        responsable.setId(L.getResponsable().getId());
        live.setResponsable((Responsable) responsable);
        if(L.getQuestions()!=null){
            live.setQuestionsList(this.MQ.Inverse(L.getQuestions()));
        }
        /*Responsable responsable = new Responsable();
        responsable.setId(L.getResponsable().getId());
        responsable.setCin(L.getResponsable().getCin());
        responsable.setSpecialite(L.getResponsable().getSpecialite());
        responsable.setInpe(L.getResponsable().getInpe());
        responsable.setPpr(L.getResponsable().getPpr());
        responsable.setEstGeneraliste(L.getResponsable().getEstGeneraliste());
        responsable.setEstMedcinESJ(L.getResponsable().getEstMedcinESJ());

        // Associez le responsable à la live
       // live.setResponsable(responsable);

        return live;
    }
    */
    public  Live  dtoLiveForCreationToLive(LiveForCreationDTO liveRequest, Responsable responsable, Administrateur admin){
        Live live = new Live();
        //Responsable R=new Responsable();
        //ProfessionnelSante P=new ProfessionnelSante();
        live.setId(liveRequest.getId());
        live.setSubject(liveRequest.getSubject());
        live.setDate(liveRequest.getDate());
        live.setLienStreamYard(liveRequest.getLienStreamYard());
        live.setLienYoutube(liveRequest.getLienYoutube());
        live.setAdmin(admin);
        live.setThematiques(mapperTheme.Iversing(liveRequest.getThematique()));
        // Associez le responsable à la live
        live.setResponsable(responsable);
        //List<PropositionThématique> P=this.M.Inverse(liveRequest.getThématiques());
        //T.setJeunes(P.getJeunes());
        return live;
    }
}

