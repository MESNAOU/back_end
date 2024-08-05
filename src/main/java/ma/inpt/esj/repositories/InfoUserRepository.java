package ma.inpt.esj.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.inpt.esj.entities.InfoUser;

public interface InfoUserRepository extends JpaRepository<InfoUser,Long> {
    Optional<InfoUser> findByMail(String mail);
    boolean existsByMail(String mail);
}

