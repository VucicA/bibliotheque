package fr.limayrac.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "livre")
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Titre", length = 50)
    private String titre;

    @Column(name = "Auteur", length = 50)
    private String auteur;

    @Column(name = "Resume", length = 250)
    private String resume;

    @Column(name = "Quantite", length = 50)
    private int quantite = 1;

    @OneToMany
    @JoinColumn(name = "idLivre")
    private List<Emprunt> emprunts = new ArrayList<>();

    @Override
    public String toString() {
        return "Compte [id = " + id + ", numero = " + titre;
    }
}