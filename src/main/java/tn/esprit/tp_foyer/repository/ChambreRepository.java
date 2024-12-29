package tn.esprit.tp_foyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tp_foyer.entity.Chambre;
import tn.esprit.tp_foyer.entity.TypeChambre;
import tn.esprit.tp_foyer.entity.Universite;

import java.util.List;
import java.awt.*;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre,Long> {
    List<Chambre> findAllByNumeroChambre(List<Long> numChambre);
    //methode JPQL
    @Query("SELECT c FROM Chambre c" +" WHERE c.bloc.idBolc = :idBloc" + " AND c.typeC = :type")
    //methode Keyword
    List<Chambre> findChambresByBlocAndType(@Param("idBloc") Long idBloc, @Param("type")TypeChambre typeChambre);
    List<Chambre> findAllByBlocUniversite(Universite universite);
}
