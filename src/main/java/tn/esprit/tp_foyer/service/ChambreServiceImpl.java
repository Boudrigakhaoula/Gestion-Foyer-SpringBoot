package tn.esprit.tp_foyer.service;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tp_foyer.entity.*;
import tn.esprit.tp_foyer.repository.BlocRepository;
import tn.esprit.tp_foyer.repository.ChambreRepository;
import tn.esprit.tp_foyer.repository.ReservationRepository;
import tn.esprit.tp_foyer.repository.UniversiteRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChambreServiceImpl implements IChambreService {
    ChambreRepository chambreRepository;
    UniversiteRepository universiteRepository;
    BlocRepository blocRepository;
    ReservationRepository reservationRepository;

    public List<Chambre> retrieveAllChambres() {
        return chambreRepository.findAll();
    }
    public Chambre retrieveChambre(long chambreId) {
        return chambreRepository.findById(chambreId).get();
    }
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }
    public void removeChambre(long chambreId) {
        chambreRepository.deleteById(chambreId);
    }
    public Chambre updateChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }
    public List<Chambre> findChambresByBlocAndType(long idBloc, TypeChambre typeChambre){
        //JPQL
        return chambreRepository.findChambresByBlocAndType(idBloc, typeChambre );

    }
    @Override
    public List<Chambre> getChambresParNomUniversite(String nomUniversite) {
        // Récupérer l'université par son nom
        Universite universite = universiteRepository.findByNameUniversite(nomUniversite)
                .orElseThrow(() -> new RuntimeException("Université introuvable"));

        // Récupérer tous les blocs associés à l'université

        List<Bloc> blocs = blocRepository.findByUniversite(universite);

        // Récupérer les chambres associées à ces blocs
        List<Chambre> chambres = new ArrayList<>();
        for (Bloc bloc : blocs) {
            chambres.addAll(bloc.getChambres()); // Assurez-vous que Bloc a une méthode getChambres()
        }
        return chambres;
    }
    @Override
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC) {
        // Solution JPQL
        List<Chambre> chambresJPQL = chambreRepository.findChambresByBlocAndType(idBloc, typeC);

        // Solution avec Keywords
        List<Chambre> chambresKeywords = chambreRepository.findChambresByBlocAndType(idBloc, typeC);

        return chambresJPQL; // ou return chambresKeywords;
    }
    @Override
    public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type) {
        // Récupérer l'université par son nom
        Universite universite = universiteRepository.findByNameUniversite(nomUniversite)

                .orElseThrow(() -> new RuntimeException("Université introuvable"));

        // Récupérer la date de l'année universitaire actuelle
        Date anneeUniversitaireActuelle = new Date(System.currentTimeMillis()); // Remplacez par votre logique pour obtenir l'année universitaire

        // Récupérer toutes les chambres du foyer
        List<Chambre> chambres = chambreRepository.findAllByBlocUniversite(universite);

        // Récupérer les réservations pour l'année universitaire actuelle et le type de chambre
        List<Chambre> chambresReservees = reservationRepository.findByAnneeUniversitaireAndTypeChambre(anneeUniversitaireActuelle, type);

        // Filtrer les chambres non réservées
        return chambres.stream()
                .filter(chambre -> !chambresReservees.contains(chambre))
                .collect(Collectors.toList());
    }

    @Override
    public List<Chambre> getChambresNonReserveesPourToutesLesUniversites() {
        // Récupérer toutes les chambres
        List<Chambre> toutesLesChambres = chambreRepository.findAll();

        // Récupérer toutes les réservations pour l'année universitaire actuelle
        Date anneeUniversitaireActuelle = new Date(System.currentTimeMillis()); // Remplacez par votre logique pour obtenir l'année universitaire
        List<Reservation> toutesLesReservations = reservationRepository.findByAnneeUniversitaire(anneeUniversitaireActuelle);

        // Récupérer les identifiants des chambres réservées
        List<Long> chambresReserveesIds = toutesLesReservations.stream()
                .map(reservation -> reservation.getIdReservation()
                       ) // Assurez-vous que vous avez une méthode pour obtenir l'ID de la reservation
                .collect(Collectors.toList());

        // Filtrer les chambres non réservées
        List<Chambre> chambresNonReservees = toutesLesChambres.stream()
                .filter(chambre -> !chambresReserveesIds.contains(chambre.getIdChambre()))
                .collect(Collectors.toList());

        return chambresNonReservees;
    }
}
