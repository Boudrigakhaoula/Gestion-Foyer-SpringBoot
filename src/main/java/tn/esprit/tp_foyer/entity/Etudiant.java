package tn.esprit.tp_foyer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEtudiant;

    private String nomEt;

    private String prenomEt;
    private long cin;
    private String ecole;
    private Date dateNaissance;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "etudiants")
    private Set<Reservation> reservations;

}
