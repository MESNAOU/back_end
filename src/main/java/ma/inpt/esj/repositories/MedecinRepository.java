package ma.inpt.esj.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import ma.inpt.esj.entities.Medecin;

public interface MedecinRepository extends JpaRepository<Medecin,Long> {
    boolean existsByCin(String cin);
    boolean existsByInpe(String inpe);
    boolean existsByPpr(String ppr);
}
