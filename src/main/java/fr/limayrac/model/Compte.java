package fr.limayrac.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "compte")
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numeroCompte", length = 50)
    private int numeroCompte;

    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;

    @Override
    public String toString() {
        return "Compte [id = " + id + ", numero = " + numeroCompte;
    }
}