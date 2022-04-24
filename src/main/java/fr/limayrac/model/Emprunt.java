package fr.limayrac.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "emprunt")
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dateEmprunt", length = 50)
    private String dateEmprunt;

    @ManyToOne
    @JoinColumn(name = "idLivre")
    private Livre livre;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
}
