package ma.inpt.esj.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public  class InfoUser {
    @Id
    /*
    @Column(name="id_user",columnDefinition = "INTEGER",nullable = false)*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //@Column(name="nom",columnDefinition = "TEXT",nullable = false)
    private String nom;

    //@Column(name="prenom",columnDefinition = "TEXT",nullable = false)
    private String prenom;

    //@Column(name="telephone",columnDefinition = "TEXT",nullable = false)
    private String numTel;
    
   // @Column(name="mail",columnDefinition = "TEXT",nullable = false)
    @Column(unique = true)
    private String mail;

    private String motDePasse;
}

