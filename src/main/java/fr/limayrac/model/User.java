package fr.limayrac.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @Column(nullable = false, length = 100)
    private String password;
    @Column(name = "nom", nullable = false, length = 20)
    private String nom;
    @Column(name = "prenom", nullable = false, length = 20)
    private String prenom;
    @Column(name = "role", nullable = false, length = 20)
    private String role = "Lecteur";
    @OneToMany(mappedBy = "user")
    private List<Emprunt> emprunts = new ArrayList<>();
}