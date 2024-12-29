package tn.esprit.tp_foyer.service;

import tn.esprit.tp_foyer.entity.Chambre;
import tn.esprit.tp_foyer.entity.Etudiant;
import tn.esprit.tp_foyer.entity.Reservation;
import tn.esprit.tp_foyer.entity.Universite;
import tn.esprit.tp_foyer.repository.ChambreRepository;
import tn.esprit.tp_foyer.repository.EtudiantRepository;
import tn.esprit.tp_foyer.repository.ReservationRepository;
import tn.esprit.tp_foyer.repository.UniversiteRepository;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReservationServiceImpl implements IReservationService {
    ReservationRepository reservationRepository;
    EtudiantRepository etudiantRepository;
    ChambreRepository chambreRepository;
    UniversiteRepository universiteRepository;


    @Override
    public List<Reservation> retrieveAllReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservation(Reservation res) {
        return reservationRepository.save(res);
    }

    @Override
    public Reservation retrieveReservation(String idReservation) {
        return reservationRepository.findById(Long.valueOf(idReservation)).get();

    }
    @Override
    public Reservation ajouterReservation(long idBloc, long cinEtudiant) {
        // Récupérer la chambre et l'étudiant
        Chambre chambre = chambreRepository.findById(idBloc).orElse(null);
        Etudiant etudiant = etudiantRepository.findById(cinEtudiant).orElse(null);

        if (chambre == null || etudiant == null) {
            throw new RuntimeException("Chambre ou étudiant introuvable");
        }

        // Récupérer le type de chambre
        String typeChambre = chambre.getTypeC().name();
        int capaciteMax = 0;

        // Déterminer la capacité maximale en fonction du type de chambre
        switch (typeChambre) {
            case "SIMPLE":
                capaciteMax = 1;
                break;
            case "DOUBLE":
                capaciteMax = 2;
                break;
            case "TRIPLE":
                capaciteMax = 3;
                break;
            default:
                throw new RuntimeException("Type de chambre inconnu");
        }

        // Compter le nombre de réservations actives pour la chambre
        long nombreReservationsActives = reservationRepository.findAll().stream()
                .filter(r -> r.getIdReservation() == chambre.getIdChambre() && r.isEstValide())
                .count();

        // Vérifier la capacité de la chambre
        if (nombreReservationsActives >= capaciteMax) {
            throw new RuntimeException("Capacité maximale de la chambre atteinte");
        }

        // Créer la réservation
        Reservation reservation = new Reservation();
        reservation.setAnneeUniversitaire(new Date(System.currentTimeMillis())); // Date actuelle
        reservation.setEstValide(true);

        // Générer le numéro de réservation pour usage interne
        String numReservation = chambre.getNumeroChambre() + "-" + chambre.getBloc().getNomBloc() + "-" + reservation.getAnneeUniversitaire();
        System.out.println("Numéro de réservation généré : " + numReservation);

        // Ajouter l'étudiant à la réservation
        Set<Etudiant> etudiants = new HashSet<>();
        etudiants.add(etudiant);
        reservation.setEtudiants(etudiants);

        // Enregistrer la réservation
        return reservationRepository.save(reservation);
    }
    @Override
    public Reservation annulerReservation(long cinEtudiant) {
        // Récupérer l'étudiant
        Etudiant etudiant = etudiantRepository.findById(cinEtudiant).orElse(null);
        if (etudiant == null) {
            throw new RuntimeException("Étudiant introuvable");
        }

        // Récupérer la réservation associée à l'étudiant
        List<Reservation> reservations = reservationRepository.findAll();
        Reservation reservationToCancel = null;

        for (Reservation reservation : reservations) {
            if (reservation.getEtudiants().contains(etudiant)) {
                reservationToCancel = reservation;
                break;
            }
        }

        if (reservationToCancel == null) {
            throw new RuntimeException("Aucune réservation trouvée pour cet étudiant");
        }

        // Mettre à jour l'état de la réservation
        reservationToCancel.setEstValide(false);

        // Désaffecter l'étudiant de la réservation
        Set<Etudiant> etudiants = reservationToCancel.getEtudiants();
        etudiants.remove(etudiant);
        reservationToCancel.setEtudiants(etudiants);

        // Désaffecter la chambre associée
        Chambre chambre = chambreRepository.findById(reservationToCancel.getIdReservation()).orElse(null);
        if (chambre != null) {
            // Récupérer le type de chambre
            String typeChambre = chambre.getTypeC().name();
            int capaciteMax = 0;

            // Déterminer la capacité maximale en fonction du type de chambre
            switch (typeChambre) {
                case "SIMPLE":
                    capaciteMax = 1;
                    break;
                case "DOUBLE":
                    capaciteMax = 2;
                    break;
                case "TRIPLE":
                    capaciteMax = 3;
                    break;
                default:
                    throw new RuntimeException("Type de chambre inconnu");
            }

            // Compter le nombre de réservations actives pour la chambre
            long nombreReservationsActives = reservations.stream()
                    .filter(r -> r.getIdReservation() == chambre.getIdChambre() && r.isEstValide())
                    .count();

            // Logique pour gérer la capacité
            if (nombreReservationsActives < capaciteMax) {
                // Actions supplémentaires si nécessaire
            }
        }

        // Enregistrer la réservation mise à jour
        return reservationRepository.save(reservationToCancel);
    }

    @Override
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversite, String nomUniversite) {
        // Récupérer l'université par son nom
        Universite universite = universiteRepository.findByNameUniversite(nomUniversite)
                .orElseThrow(() -> new RuntimeException("Université introuvable"));

        // Récupérer les réservations pour l'année universitaire et l'université
        return reservationRepository.findByAnneeUniversitaireAndUniversite(anneeUniversite, universite);
    }

}
