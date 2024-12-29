package tn.esprit.tp_foyer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tp_foyer.entity.Chambre;
import tn.esprit.tp_foyer.entity.Foyer;
import tn.esprit.tp_foyer.service.IFoyerService;

import java.util.List;

@Tag(name = "Gestion Foyer")
@RestController
@AllArgsConstructor
@RequestMapping(name = "/foyer")
public class FoyerRestController {
    IFoyerService foyerService;

    // http://localhost:8080/tpfoyer/foyer/retrieve-all-foyers
    @Operation(description = "récupérer tous les foyers de la base de données")
    @GetMapping("/retrieve-all-foyer")
    public List<Foyer> retrieveAllFoyer() {
        List<Foyer> listFoyers = foyerService.retriveAllFoyers();
        return listFoyers;
    }

    // http://localhost:8080/tpfoyer/foyer/retrieve-foyer/8
    @GetMapping("/retrieve-foyer/{foyer-id}")
    public Foyer retrieveFoyer(@PathVariable("foyer-id") long foId) {
        Foyer foyer = foyerService.retriveFoyer(foId);
        return foyer;
    }

    // http://localhost:8080/tpfoyer/foyer/add-foyer
    @PostMapping("/add-foyer")
    public Foyer addFoyer(@RequestBody Foyer f) {
        Foyer foyer = foyerService.addFoyer(f);
        return foyer;
    }

    // http://localhost:8080/tpfoyer/foyer/remove-foyer/{foyer-id}
    @DeleteMapping("/remove-foyer/{foyer-id}")
    public void removeFoyer(@PathVariable("foyer-id") long foId) {
        foyerService.removeFoyer(foId);
    }

    // http://localhost:8080/tpfoyer/foyer/modify-foyer
    @PutMapping("/update-foyer")
    public Foyer updateFoyer(@RequestBody Foyer f) {
        Foyer foyer = foyerService.updateFoyer(f);
        return foyer;
    }

    // http://localhost:8080/tpfoyer/foyer/ajouter-foyer-et-affecter-universite
    @PostMapping("/ajouter-foyer-et-affecter-universite")
    public Foyer ajouterFoyerEtAffecterAUniversite(@RequestBody Foyer foyer, @RequestParam long idUniversite) {
        Foyer savedFoyer = foyerService.ajouterFoyerEtAffecterUniversite(foyer, idUniversite);
        return savedFoyer;
    }

}
