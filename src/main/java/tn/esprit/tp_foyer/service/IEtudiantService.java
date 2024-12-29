package tn.esprit.tp_foyer.service;

import tn.esprit.tp_foyer.entity.Etudiant;
import tn.esprit.tp_foyer.entity.Foyer;

import java.util.List;

public interface IEtudiantService {
    public List<Etudiant> retriveAllEtudiants();
    public List<Etudiant> addEtudiants(List<Etudiant> etudiants);
    public Etudiant updateEtudiant(Etudiant et);
    public Etudiant retriveEtudiant(long idEtudiant);
    public void removeEtudiant(long idEtudiant);
}
