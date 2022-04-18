package fr.limayrac.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import  fr.limayrac.model.Compte;

@Repository
public interface CompteRepository extends CrudRepository<Compte, Integer>{

}