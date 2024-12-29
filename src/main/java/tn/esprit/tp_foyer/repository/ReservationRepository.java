package tn.esprit.tp_foyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tp_foyer.entity.Chambre;
import tn.esprit.tp_foyer.entity.Reservation;
import tn.esprit.tp_foyer.entity.TypeChambre;
import tn.esprit.tp_foyer.entity.Universite;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.anneeUniversitaire = :anneeUniversitaire ")
    List<Reservation> findByAnneeUniversitaireAndUniversite(@Param("anneeUniversitaire") Date anneeUniversitaire, @Param("universite") Universite universite);

    List<Chambre> findByAnneeUniversitaireAndTypeChambre(@Param("anneeUniversitaire") Date anneeUniversitaire, @Param("type") TypeChambre type);

    @Query("SELECT r FROM Reservation r WHERE r.anneeUniversitaire = :anneeUniversitaire")
    List<Reservation> findByAnneeUniversitaire(@Param("anneeUniversitaire") Date anneeUniversitaire);
}

