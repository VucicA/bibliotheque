package fr.limayrac.repository;

import fr.limayrac.model.Emprunt;
import fr.limayrac.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    @Query("SELECT e FROM Emprunt e WHERE e.livre = ?1")
    public Emprunt findByLivre(Long id);
}