package fr.limayrac.service;

import fr.limayrac.model.Livre;
import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.limayrac.repository.LivreRepository;

import java.util.Optional;

@Data
@Service
public class LivreService {

    @Autowired
    private LivreRepository livreRepository;

    public Optional<Livre> getLivre(final Long id) {
        return livreRepository.findById(id);
    }

    public Iterable<Livre> getLivres() {
        return livreRepository.findAll();
    }

    public void deleteLivre(final Long id) {
        livreRepository.deleteById(id);
    }

    public Livre saveLivre(Livre livre) {
        return livreRepository.save(livre);
    }
}