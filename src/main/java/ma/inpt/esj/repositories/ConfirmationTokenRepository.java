package ma.inpt.esj.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import ma.inpt.esj.entities.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {
    ConfirmationToken findByToken(String token);
}
