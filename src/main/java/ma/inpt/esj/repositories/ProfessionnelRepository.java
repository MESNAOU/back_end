

package ma.inpt.esj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.inpt.esj.entities.ProfessionnelSante;

public interface ProfessionnelRepository extends JpaRepository<ProfessionnelSante, Long> {
    boolean existsByCin(String cin);
    boolean existsByInpe(String inpe);
}
