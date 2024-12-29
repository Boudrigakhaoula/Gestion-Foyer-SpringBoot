package tn.esprit.tp_foyer.service;
import tn.esprit.tp_foyer.entity.Chambre;
import tn.esprit.tp_foyer.entity.TypeChambre;

import java.util.List;

public interface IChambreService {

    public List<Chambre> retrieveAllChambres();
    public Chambre retrieveChambre(long chambreId);
    public Chambre addChambre(Chambre c);
    public void removeChambre(long chambreId);
    public Chambre updateChambre(Chambre chambre);
    public List<Chambre> getChambresParNomUniversite(String nomUniversite);
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC);
    public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type);
    public List<Chambre> getChambresNonReserveesPourToutesLesUniversites();

}
