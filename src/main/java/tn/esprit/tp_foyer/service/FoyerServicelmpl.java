package tn.esprit.tp_foyer.service;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import tn.esprit.tp_foyer.entity.Bloc;
import tn.esprit.tp_foyer.entity.Foyer;
import tn.esprit.tp_foyer.entity.Universite;
import tn.esprit.tp_foyer.repository.FoyerRepository;
import tn.esprit.tp_foyer.repository.UniversiteRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FoyerServicelmpl implements IFoyerService{
    FoyerRepository foyerRepository;
    UniversiteRepository universiteRepository;
    public List<Foyer> retriveAllFoyers(){
        return foyerRepository.findAll();
    }

    @Override
    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public Foyer updateFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public Foyer retriveFoyer(long idFoyer) {
        return foyerRepository.findById(idFoyer).get();
    }

    @Override
    public void removeFoyer(long idFoyer) {
        foyerRepository.deleteById(idFoyer);
    }
    public Foyer ajouterFoyerEtAffecterUniversite(Foyer foyer, long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);
        foyer.setUniversite(universite);
        // Assurer que les blocs sont correctement associés au foyer
        if (foyer.getBlocs() != null) {
            for (Bloc bloc : foyer.getBlocs()) {
                bloc.setFoyer(foyer);
            }
        }
        Foyer savedFoyer = foyerRepository.save(foyer);
        if (universite != null) {
            universite.setFoyer(savedFoyer);
            universiteRepository.save(universite);
        }
        return savedFoyer;
    }

}
