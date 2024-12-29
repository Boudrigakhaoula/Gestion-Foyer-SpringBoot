package tn.esprit.tp_foyer.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tp_foyer.entity.Bloc;
import tn.esprit.tp_foyer.entity.Chambre;
import tn.esprit.tp_foyer.repository.BlocRepository;
import tn.esprit.tp_foyer.repository.ChambreRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class BlocServiceImpl implements IBlocService{
    @Autowired
   BlocRepository blocRepository;
   @Autowired
   private ChambreRepository chambreRepository;
    @Override
    public List<Bloc> retrieveBlocs() {
        return blocRepository.findAll();

    }

    @Override
    public Bloc updateBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc addBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc retrieveBloc(long idBloc) {
        return blocRepository.findById(idBloc).get();
    }

    @Override
    public void deleteBloc(long idBloc) {
        blocRepository.deleteById(idBloc);
    }
    @Override
    public Bloc affecterChambresABloc(List<Long> numChambres , long idBloc) {
        Bloc bloc = blocRepository.findById(idBloc).orElse(null);
        List<Chambre> chambres = chambreRepository.findAllByNumeroChambre(numChambres);
        if (chambres.size() != numChambres.size()){
            throw new RuntimeException("une ou plusieurs chambres sont interouvable");}
        for (Chambre chambre : chambres)
            if (chambre.getBloc()!= null && chambre.getBloc().getIdBolc()!= idBloc){
                throw new RuntimeException("la chambre" + chambre.getNumeroChambre()+"est deja affectee a un autre bloc");
            }

        for (Chambre chambre : chambres){chambre.setBloc(bloc);}
        //bloc.getChambres().addAll(chambres);
        chambreRepository.saveAll(chambres);
        return bloc;
    }
    //creation du service qui se déclenche toutes les minutes , affichier la liste des chambres du bloc
    @Scheduled(cron = "*/59 * * * * *")
    public void listeChambresParBloc(){
        List<Bloc> blocs = blocRepository.findAll();
        for (Bloc bloc : blocs) {
            log.info("****************************");
            log.info("Bloc => " + bloc.getNomBloc()+ "ayant une capacité " + bloc.getCapaciteBloc());
            if (bloc.getChambres()==null || bloc.getChambres().isEmpty()){
                log.info("Pas de chambre disponible dans ce bloc");
            }
            else {
                log.info("la liste des chambres pour ce bloc : ");
                List<Chambre> chambresList = bloc.getChambres();
                chambresList.forEach(chambre -> {
                    log.info("NumChambre : " + chambre.getNumeroChambre() + "type : " + chambre.getTypeC());
                });

            }
            log.info("****************************");
        }
    }
    @Scheduled(cron = "* */2 * * * *")
    public void pourcentageChambreParTypeChambre(){
        List<Chambre> chambres = chambreRepository.findAll();
        //nbr total de chambres
        int totalChambre = chambres.size();
        log.info("Nombre total des chambres : " + totalChambre);
        if (totalChambre > 0) {
            Map<String, Integer> countBytype = new HashMap<>();
            for (Chambre chambre : chambres) {
                String type = String.valueOf(chambre.getTypeC());
                countBytype.put(type, countBytype.getOrDefault(type, 0) + 1);
            }
            for (Map.Entry<String, Integer> entry : countBytype.entrySet()) {
                String type = entry.getKey();
                int count = entry.getValue();
                double pourcentage = (count * 100.0) / totalChambre;
                log.info("Pourcentage des chambres pour le type : " + type + "est egale" + pourcentage);
            }
        }
        else {
            log.info("Aucun chambre trouvée");
        }
    }
}
