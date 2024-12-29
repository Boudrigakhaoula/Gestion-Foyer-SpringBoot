package tn.esprit.tp_foyer.service;

import tn.esprit.tp_foyer.entity.Foyer;

import java.util.List;

public interface IFoyerService {
    public List<Foyer> retriveAllFoyers();
    public Foyer addFoyer(Foyer f);
    public Foyer updateFoyer(Foyer f);
    public Foyer retriveFoyer(long idFoyer);
    public void removeFoyer(long idFoyer);
    public Foyer ajouterFoyerEtAffecterUniversite(Foyer foyer, long idUniversite);
}
