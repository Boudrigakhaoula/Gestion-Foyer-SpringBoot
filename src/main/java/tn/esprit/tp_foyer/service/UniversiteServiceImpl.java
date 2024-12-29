package tn.esprit.tp_foyer.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tp_foyer.entity.Foyer;
import tn.esprit.tp_foyer.entity.Universite;
import tn.esprit.tp_foyer.repository.FoyerRepository;
import tn.esprit.tp_foyer.repository.UniversiteRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UniversiteServiceImpl implements IUniversiteService {
    @Autowired
    UniversiteRepository universiteRepository;
    @Autowired
    private FoyerRepository foyerRepository;

    @Override
    public List<Universite> retrieveAllUniversite() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite updateUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite retrieveUniversite(long idUniversite) {
        return universiteRepository.findById(idUniversite).get();
    }

    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);
        Universite universite = universiteRepository.findByNameUniversite(nomUniversite).orElse(null);
        if (foyer.getUniversite() != null || universite.getFoyer() != null)
            throw new RuntimeException("L'association existe deja pour ce foyer ou cette universite");
            //Associer le foyer à l'universite
            universite.setFoyer(foyer);
            foyer.setUniversite(universite);
            //Sauvergarder les changements
            universiteRepository.save(universite);
            foyerRepository.save(foyer);
        return universite;

    }
    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);
        if (universite.getFoyer() == null) {
            throw new RuntimeException("Aucun foyer n'est actuellement associé à cette universite ");
        }
        //Désaffeter le foyer de l'universite
        Foyer foyer = universite.getFoyer();
        universite.setFoyer(null);
        foyer.setUniversite(null);
        //save
        universiteRepository.save(universite);
        foyerRepository.save(foyer);

        return universite;
    }
}
