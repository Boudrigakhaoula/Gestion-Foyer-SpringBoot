package tn.esprit.tp_foyer.service;

import tn.esprit.tp_foyer.entity.Universite;

import java.util.List;

public interface IUniversiteService {
    public List<Universite> retrieveAllUniversite();
    public Universite addUniversite(Universite u);
    public Universite updateUniversite(Universite u);
    public Universite retrieveUniversite(long idUniversite);
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite);
    public Universite desaffecterFoyerAUniversite(long idUniversite);
    
}
