package tn.esprit.tp_foyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tp_foyer.entity.Etudiant;

import javax.swing.*;
import java.sql.Date;
import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {

/*
    @Override
    List<Etudiant> findAll();

    List<Etudiant> findByDateNaissanceBetween(Date dateD, Date dateF);
*/
}

