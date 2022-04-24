package fr.limayrac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  fr.limayrac.model.Livre;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {

}