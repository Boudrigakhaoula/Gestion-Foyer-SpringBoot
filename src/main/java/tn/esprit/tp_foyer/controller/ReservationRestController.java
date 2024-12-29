package tn.esprit.tp_foyer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp_foyer.entity.Chambre;
import tn.esprit.tp_foyer.entity.Reservation;
import tn.esprit.tp_foyer.entity.Universite;
import tn.esprit.tp_foyer.service.IReservationService;

import java.sql.Date;
import java.util.List;

@Tag(name = "Gestion reservation")
@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationRestController {
    IReservationService reservationService;

    // http://localhost:8080/tpfoyer/reservation/retrieve-all-reservations
    @Operation(description = "récupérer tous les chambres de la base de données")
    @GetMapping("/retrieve-all-chambres")
    public List<Reservation> retrieveAllReservation() {
        List<Reservation> listReservations = reservationService.retrieveAllReservation();
        return listReservations;
    }

    // http://localhost:8080/foyer/reservation/retrieve-reservation/8
    @GetMapping("/retrieve-chambre/{chambre-id}")
    public Reservation retrieveReservation(@PathVariable("reservation-id") String idReservation) {
        Reservation reservation = reservationService.retrieveReservation(idReservation);
        return reservation;
    }



    // http://localhost:8080/foyer/reservation/update-reservation
    @PutMapping("/update-reservation")
    public Reservation updateReservation(@RequestBody Reservation r) {
        Reservation reservation = reservationService.updateReservation(r);
        return reservation;
    }

    // http://localhost:8080/tpfoyer/reservation/ajouter-reservation
    @PostMapping("/ajouter-reservation")
    public Reservation ajouterReservation(@RequestParam long idBloc, @RequestParam long cinEtudiant) {
        return reservationService.ajouterReservation(idBloc, cinEtudiant);
    }

    // http://localhost:8080/tpfoyer/reservation/annuler-reservation
    @PostMapping("/annuler-reservation")
    public Reservation annulerReservation(@RequestParam long cinEtudiant) {
        return reservationService.annulerReservation(cinEtudiant);
    }
    // http://localhost:8080/tpfoyer/reservation/getReservationParAnneeUniversitaireEtNomUniversite
    @GetMapping("/getReservationParAnneeUniversitaireEtNomUniversite")
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(@RequestParam Date anneeUniversitaire, @RequestParam String nomUniversite) {
        return reservationService.getReservationParAnneeUniversitaireEtNomUniversite(anneeUniversitaire, nomUniversite);
    }
}


