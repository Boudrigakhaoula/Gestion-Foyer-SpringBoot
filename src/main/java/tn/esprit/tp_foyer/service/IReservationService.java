package tn.esprit.tp_foyer.service;

import tn.esprit.tp_foyer.entity.Reservation;

import java.sql.Date;
import java.util.List;

public interface IReservationService {
    public List<Reservation> retrieveAllReservation();
    public Reservation updateReservation(Reservation res);
    public Reservation retrieveReservation(String idReservation);
    public Reservation ajouterReservation(long idBloc, long cinEtudiant);
    public Reservation annulerReservation(long cinEtudiant);
    public  List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversite, String nomUniversite);
}
