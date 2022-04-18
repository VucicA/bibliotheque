package fr.limayrac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import  fr.limayrac.model.Client;


public interface ClientRepository extends JpaRepository<Client, Long>{
    @Query("SELECT u FROM Client u WHERE u.email = ?1")
    public Client findByEmail(String email);
}